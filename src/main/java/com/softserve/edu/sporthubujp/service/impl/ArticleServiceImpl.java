package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.sporthubujp.dto.ArticleListDTO;
import com.softserve.edu.sporthubujp.dto.ArticleSaveDTO;
import com.softserve.edu.sporthubujp.dto.CommentDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import com.softserve.edu.sporthubujp.exception.EntityNotExistsException;
import com.softserve.edu.sporthubujp.exception.ArticleServiceException;
import com.softserve.edu.sporthubujp.mapper.ArticleMapper;
import com.softserve.edu.sporthubujp.repository.ArticleRepository;
import com.softserve.edu.sporthubujp.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;


import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {

    private static final String ARTICLE_NOT_FOUND_BY_ID = "Article not found by id: %s";
    private static final String ARTICLE_NOT_DELETE_BY_ID = "Record with provided id: %s is not found";

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository,
        ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
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
    public void deleteArticleById(String id)
    {
        log.info("Delete article by id in service");
        if(!articleRepository.existsById(id))
        {
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
        articles = articleRepository.getArticlesByTeamId(idUser,teamId);
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
    
    public List<ArticleDTO> getAllArticles(){
        List<Article> articles = new LinkedList<Article>();
        articles = articleRepository.findAll();
        log.info("Get all article in service");
        List<ArticleDTO> articleDTOS = new LinkedList<ArticleDTO>();
        for (var article : articles) {
            articleDTOS.add(articleMapper.entityToDto(article));
        }
        return articleDTOS;
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
        log.info("Get all articles by category id {} and if article.active {}", categoryId,isActive);
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
}
