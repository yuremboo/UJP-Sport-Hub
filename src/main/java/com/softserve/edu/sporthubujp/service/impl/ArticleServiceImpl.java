package com.softserve.edu.sporthubujp.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.google.common.base.Converter;
import com.google.common.base.Converter;
import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.dto.ArticleListDTO;
import com.softserve.edu.sporthubujp.dto.ArticleSaveDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import com.softserve.edu.sporthubujp.entity.Category;
import com.softserve.edu.sporthubujp.entity.Team;
import com.softserve.edu.sporthubujp.exception.ArticleServiceException;
import com.softserve.edu.sporthubujp.exception.EntityNotExistsException;
import com.softserve.edu.sporthubujp.mapper.ArticleListMapper;
import com.softserve.edu.sporthubujp.mapper.ArticleMapper;
import com.softserve.edu.sporthubujp.repository.ArticleRepository;
import com.softserve.edu.sporthubujp.repository.CategoryRepository;
import com.softserve.edu.sporthubujp.repository.LogsRepository;
import com.softserve.edu.sporthubujp.repository.TeamRepository;
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
    private final TeamRepository teamRepository;
    private final ArticleMapper articleMapper;
    private final ArticleListMapper articleListMapper;
    private final LogsRepository logRepository;

    private final CommentService commentService;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository,
        ArticleMapper articleMapper, CommentService commentService,
        CategoryRepository categoryRepository, TeamRepository teamRepository, ArticleListMapper articleListMapper, LogsRepository logRepository) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
        this.teamRepository = teamRepository;
        this.articleListMapper = articleListMapper;
        this.logRepository = logRepository;
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
    public List<ArticleListDTO> getMorePopularArticles(Pageable pageable) {
        List<String> articlesId = logRepository
            .getMorePopularArticlesId( PageRequest.of(0, 3));
        List<Article> articles=new LinkedList<Article>();
        for (var article : articlesId) {
            articles.add(articleRepository.getArticleById(article));
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
    public List<ArticleDTO> getAllArticlesByCategoryName(String nameCategory) {
        List<Article> articles = new LinkedList<Article>();
        articles = articleRepository.getAllArticlesByCategoryName(nameCategory);
        log.info("Get all articles by category name: {}", nameCategory);
        List<ArticleDTO> articlesDTOS = new LinkedList<>();
        for (var article : articles) {
            articlesDTOS.add(articleMapper.entityToDto(article));
        }
        return articlesDTOS;
    }

    @Override
    public List<ArticleListDTO> getArticlesByTeamByUserId(String idUser, String teamId) {
        List<Article> articles = new LinkedList<>();
        articles = articleRepository.getArticlesByTeamIdAndUserId(idUser, teamId);
        log.info("Get articles by teams id subscription");
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
    public void selectedByAdminArticle(List<String> articleIDList) {
        articleRepository.setSelectByAdmin();
        List<Article> articlesList =new LinkedList<Article>();
        for (var articleId : articleIDList) {
            //articlesList.add(articleRepository.getReferenceById(articleId));
            var article1=articleRepository.findById(articleId).orElseThrow(EntityNotExistsException::new);;
            article1.setSelectedByAdmin(true);
            articleRepository.save(article1);
        }
    }
    public ArticleDTO updateArticle(ArticleSaveDTO newArticle, String id) {
        return articleRepository.findById(id)
            .map(article -> {
                article.setUpdateDateTime(LocalDateTime.now());
                Category category = categoryRepository.findById(newArticle.getCategoryId()).orElseThrow(EntityNotExistsException::new);
                Team team = teamRepository.findById(newArticle.getTeamId()).orElseThrow(EntityNotExistsException::new);

                article.setCategory(category);
                article.setTeam(team);
                articleMapper.updateArticle(article, newArticle);
                return articleMapper.entityToDto(articleRepository.save(article));
            })
            .orElseThrow(EntityNotExistsException::new);
    }

    @Override
    public Page<ArticleListDTO> getAllArticles(Pageable pageable) {
        Page<Article> articles = articleRepository.findAll(pageable);
        Page<ArticleListDTO> articleDTOPage = articles.map(new Converter<Article, ArticleListDTO>() {

            @Override
            protected ArticleListDTO doForward(Article article) {
                return new ArticleListDTO(articleMapper.entityToDto(article));
            }

            @Override
            protected Article doBackward(ArticleListDTO articleListDTO) {
                return articleListMapper.dtoToEntity(articleListDTO);
            }
        });

        log.info("Get all articles in service");
        int total = articles.getTotalPages();
        return articleDTOPage;
    }

    @Override
    public Page<ArticleListDTO> getAllArticlesByCategoryId(String categoryId, Pageable pageable) {
        Page<Article> articles = articleRepository.findAllByCategoryId(categoryId, pageable);
        log.info("Get all articles by category id in service");
        Page<ArticleListDTO> articleDTOPage = articles.map(new Converter<Article, ArticleListDTO>() {

            @Override
            protected ArticleListDTO doForward(Article article) {
                return new ArticleListDTO(articleMapper.entityToDto(article));
            }

            @Override
            protected Article doBackward(ArticleListDTO articleListDTO) {
                return articleListMapper.dtoToEntity(articleListDTO);
            }
        });

        return articleDTOPage;
    }

    @Override
    public Page<ArticleListDTO> getAllArticlesByCategoryIdAndIsActive(String categoryId, boolean isActive, Pageable pageable) {
        Page<Article> articles = articleRepository.findAllByCategoryIdAndIsActive(categoryId, isActive, pageable);
        log.info("Get all articles by category id {} and if article.active {}", categoryId, isActive);
        Page<ArticleListDTO> articleDTOPage = articles.map(new Converter<Article, ArticleListDTO>() {

            @Override
            protected ArticleListDTO doForward(Article article) {
                return new ArticleListDTO(articleMapper.entityToDto(article));
            }

            @Override
            protected Article doBackward(ArticleListDTO articleListDTO) {
                return articleListMapper.dtoToEntity(articleListDTO);
            }
        });

        return articleDTOPage;
    }

    @Override
    public List<ArticleListDTO> getSixActiveArticlesByCategoryId(String categoryId, String articleId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new EntityNotExistsException(String.format(CATEGORY_NOT_FOUND_BY_ID, categoryId));
        }
        Pageable sortedByCreateDateTime = PageRequest.of(0, 6,
            Sort.by("create_date_time").descending());

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
        List<ArticleListDTO> allArticlesDTOS = getAllArticles(Pageable.unpaged()).getContent();
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

    public List<ArticleListDTO> getNewestArticlesByCategoryId(String categoryId, Pageable pageable) {
        List<Article> articles = articleRepository
            .findNewestArticlesByCategoryId(categoryId, PageRequest.of(0, 4))
            .orElseThrow(EntityNotFoundException::new);

        log.info("Service: getting four newest articles by category id");

        return articles.stream()
            .map(article -> new ArticleListDTO(articleMapper.entityToDto(article)))
            .collect(Collectors.toList());
    }


    public ArticleDTO publishUnpublishedArticle(String id) {
        return articleRepository.findById(id)
            .map(article -> {
                article.setIsActive(!article.getIsActive());
                return articleMapper.entityToDto(articleRepository.save(article));
            })
            .orElseThrow(EntityNotExistsException::new);
    }

    @Override
    public List<ArticleListDTO> getAllArticlesByTeamId(String teamId) {
        List<Article> articles = articleRepository
                .getAllArticlesByTeamId(teamId)
                .orElseThrow(EntityNotFoundException::new);

        log.info("Service: getting all articles by team id");

        return articles
                .stream()
                .map(article -> new ArticleListDTO(articleMapper.entityToDto(article)))
                .collect(Collectors.toList());
    }
}



