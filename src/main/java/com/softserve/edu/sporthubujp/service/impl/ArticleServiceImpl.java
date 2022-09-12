package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.dto.ArticleListDTO;
import com.softserve.edu.sporthubujp.dto.ArticleSaveDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import com.softserve.edu.sporthubujp.exception.EntityNotExistsException;
import com.softserve.edu.sporthubujp.exception.ArticleServiceException;
import com.softserve.edu.sporthubujp.mapper.ArticleMapper;
import com.softserve.edu.sporthubujp.repository.ArticleRepository;
import com.softserve.edu.sporthubujp.repository.LogsRepository;
import com.softserve.edu.sporthubujp.service.ArticleService;
import com.softserve.edu.sporthubujp.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {

    private static final String ARTICLE_NOT_FOUND_BY_ID = "Article not found by id: %s";
    private static final String ARTICLE_NOT_DELETE_BY_ID = "Record with provided id: %s is not found";

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final LogsRepository logRepository;

    private final CommentService commentService;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository,
                              ArticleMapper articleMapper, LogsRepository logRepository, CommentService commentService) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
        this.logRepository = logRepository;
        this.commentService = commentService;
    }

    public ArticleDTO getArticleById(String id) {
        Article article = articleRepository.getReferenceById(id);
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
    public List<ArticleListDTO> getMorePopularArticles() {
        List<String> articlesId = logRepository.getMorePopularArticlesId();
        List<Article> articles=new LinkedList<Article>();
        for (var article : articlesId) {
            articles.add(articleRepository.getReferenceById(article));
        }
        log.info("Get 3 more popular articles");
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
        int total = articles.getTotalPages();
        return articleListDTOS;
    }

    @Override
    public List<ArticleListDTO> getAllArticlesByCategoryId(String categoryId, Pageable pageable) {
        List<Article> articles = new LinkedList<>();
        articles = articleRepository.findAllByCategoryId(categoryId, pageable);
        log.info("Get all articles by category id in service");
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
    public List<ArticleListDTO> getAllArticlesByCategoryIdAndIsActive(String categoryId, boolean isActive, Pageable pageable) {
        List<Article> articles = new LinkedList<>();
        articles = articleRepository.findAllByCategoryIdAndIsActive(categoryId, isActive, pageable);
        log.info("Get all articles by category id {} and if article.active {}", categoryId, isActive);
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

    @Override
    public List<ArticleListDTO> getAllArticlesWithoutPagination() {
        List<Article> articles = new LinkedList<Article>();
        articles = articleRepository.findAll();

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

    public List<ArticleListDTO> getNewestArticlesByCategoryId(String categoryId, Pageable pageable) {
        List<Article> articles = articleRepository
                .findNewestArticlesByCategoryId(categoryId, PageRequest.of(0, 4))
                .orElseThrow(EntityNotFoundException::new);

        log.info("Service: getting four newest articles by category id");

        List<ArticleListDTO> articleListDTOs = articles
                .stream()
                .map(article -> new ArticleListDTO(articleMapper.entityToDto(article)))
                .collect(Collectors.toList());

        return articleListDTOs;
}

    public ArticleDTO publishUnpublishedArticle(String id) {
        return articleRepository.findById(id)
            .map(article -> {
                article.setIsActive(!article.getIsActive());
                return articleMapper.entityToDto(articleRepository.save(article));
            })
            .orElseThrow(EntityNotExistsException::new);
    }
}



