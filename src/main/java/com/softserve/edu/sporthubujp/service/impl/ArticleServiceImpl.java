package com.softserve.edu.sporthubujp.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.dto.ArticleListDTO;
import com.softserve.edu.sporthubujp.dto.ArticleSaveDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import com.softserve.edu.sporthubujp.exception.ArticleServiceException;
import com.softserve.edu.sporthubujp.exception.EntityNotExistsException;
import com.softserve.edu.sporthubujp.mapper.ArticleMapper;
import com.softserve.edu.sporthubujp.repository.ArticleRepository;
import com.softserve.edu.sporthubujp.repository.CategoryRepository;
import com.softserve.edu.sporthubujp.service.ArticleService;
import com.softserve.edu.sporthubujp.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {

    private static final String ARTICLE_NOT_FOUND_BY_ID = "Article not found by id: %s";
    private static final String CATEGORY_NOT_FOUND_BY_ID = "Category not found by id: %s";
    private static final String ARTICLE_NOT_DELETE_BY_ID = "Record with provided id: %s is not found";

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final ArticleMapper articleMapper;

    private final CommentService commentService;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository,
        ArticleMapper articleMapper, CommentService commentService,
        CategoryRepository categoryRepository) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
        this.commentService = commentService;
        this.categoryRepository = categoryRepository;
    }

    public ArticleDTO getArticleById(String id) {
        Article article = articleRepository.getArticleById(id);
        log.info("Get article by id in service");
        if (!articleRepository.existsById(id)) {
            log.error(String.format(ARTICLE_NOT_FOUND_BY_ID, id));
            throw new EntityNotExistsException(String.format(ARTICLE_NOT_FOUND_BY_ID, id));
        }
        return articleMapper.entityToDto(article);
    }

    @Override
    public void deleteArticleById(String id) {
        log.info("Delete article by id in service");
        if (!articleRepository.existsById(id)) {
            log.error(String.format(ARTICLE_NOT_DELETE_BY_ID, id));
            throw new ArticleServiceException(String.format(ARTICLE_NOT_DELETE_BY_ID, id));
        }
        articleRepository.deleteById(id);
    }

    @Override
    public List<ArticleDTO> getAllArticlesBySubscription(String idUser) {
        List<Article> articles = new LinkedList<Article>();
        articles = articleRepository.getAllArticlesBySubscription(idUser);
        log.info("Get all articles by subscription through user id {}", idUser);
        List<ArticleDTO> articlesDTOS = new LinkedList<>();
        for (var article : articles) {
            articlesDTOS.add(articleMapper.entityToDto(article));
        }
        return articlesDTOS;
    }

    @Override
    public List<ArticleListDTO> getArticlesByTeamByUserId(String idUser, String teamId) {
        List<Article> articles = new LinkedList<>();
        articles = articleRepository.getArticlesByTeamId(idUser, teamId);
        log.info("Get articles by teams id subscription");
        return getArticleListDTOS(articles);
    }

    public ArticleDTO updateArticle(ArticleSaveDTO newArticle, String id) {
        return articleRepository.findById(id)
            .map(article -> {
                articleMapper.updateArticle(article, newArticle);
                return articleMapper.entityToDto(articleRepository.save(article));
            })
            .orElseThrow(EntityNotExistsException::new);
    }

    public List<ArticleListDTO> getAllArticles(Pageable pageable) {
        //        List<Article> articles = new LinkedList<Article>();
        Page<Article> articles;
        articles = articleRepository.findAll(pageable);
        log.info("Get all article in service");
        List<ArticleDTO> articleDTOS = new LinkedList<ArticleDTO>();
        for (var article : articles) {
            articleDTOS.add(articleMapper.entityToDto(article));
        }

        List<ArticleListDTO> articleListDTOS = new LinkedList<>();
        for (var articleDTO : articleDTOS) {
            articleListDTOS.add(new ArticleListDTO(articleDTO));
        }
        return articleListDTOS;
    }

    @Override
    public List<ArticleListDTO> getAllArticlesByCategoryId(String categoryId, Pageable pageable) {
        List<Article> articles = new LinkedList<>();
        articles = articleRepository.findAllByCategoryId(categoryId, pageable);
        log.info("Get all articles by category id in service");
        return getArticleListDTOS(articles);
    }

    @Override
    public List<ArticleListDTO> getAllArticlesByCategoryIdAndIsActive(String categoryId, boolean isActive, Pageable pageable) {
        List<Article> articles = new LinkedList<>();
        articles = articleRepository.findAllByCategoryIdAndIsActive(categoryId, isActive, pageable);
        log.info("Get all articles by category id {} and if article.active {}", categoryId, isActive);
        return getArticleListDTOS(articles);
    }

    @NotNull
    private List<ArticleListDTO> getArticleListDTOS(List<Article> articles) {
        List<ArticleDTO> articleDTOS = new LinkedList<>();
        for (var article : articles) {
            articleDTOS.add(articleMapper.entityToDto(article));
        }

        List<ArticleListDTO> articleListDTOS = new LinkedList<>();
        for (var articleDTO : articleDTOS) {
            articleListDTOS.add(new ArticleListDTO(articleDTO));
        }
        return articleListDTOS;
    }

    @Override
    public List<ArticleListDTO> getSixActiveArticlesByCategoryId(String categoryId, String articleId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new EntityNotExistsException(String.format(CATEGORY_NOT_FOUND_BY_ID, categoryId));
        }
        Pageable sortedByCreateDateTime = PageRequest.of(0, 6,
            Sort.by("createDateTime").descending());

        List<Article> allActiveArticlesByCategoryId = articleRepository
            .findAllByCategoryIdAndIsActive(categoryId, true, sortedByCreateDateTime)
            .stream()
            .filter(article -> !Objects.equals(article.getId(), articleId))
            .collect(Collectors.toList());
        log.info("Get all active articles by category id {}", categoryId);
        return getArticleListDTOS(allActiveArticlesByCategoryId);
    }

    @Override
    public List<ArticleListDTO> getMostCommentedArticles() {
        List<ArticleListDTO> allArticlesDTOS = getAllArticles(Pageable.unpaged());
        Map<String, Integer> mapArticleComments = new HashMap<>();

        for (var article : allArticlesDTOS) {
            mapArticleComments.put(article.getId(), commentService.getNumOfCommentsByArticleId(article.getId()));
        }

        Map<String, Integer> sortedMap =
            mapArticleComments.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .collect(Collectors.toMap(
                    Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        List<ArticleDTO> mostCommentedArticleDTOS = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            mostCommentedArticleDTOS.add(getArticleById(entry.getKey()));
        }

        List<ArticleListDTO> mostCommentedArticleListDTOS = new LinkedList<>();
        for (var articleDTO : mostCommentedArticleDTOS) {
            mostCommentedArticleListDTOS.add(new ArticleListDTO(articleDTO));
        }
        return mostCommentedArticleListDTOS;
    }
}
