package com.softserve.edu.sporthubujp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.dto.ArticleListDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import com.softserve.edu.sporthubujp.mapper.ArticleMapper;
import com.softserve.edu.sporthubujp.repository.ArticleRepository;
import com.softserve.edu.sporthubujp.service.ArticleService;
import org.springframework.data.domain.Pageable;
import com.softserve.edu.sporthubujp.exception.EntityNotExistsException;
import com.softserve.edu.sporthubujp.exception.ArticleServiceException;


import java.util.LinkedList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private static final String ARTICLE_NOT_FOUND_BY_ID = "Article not found by id: %s";

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
        if (article == null) {
            throw new EntityNotExistsException(String.format(ARTICLE_NOT_FOUND_BY_ID, id));
        }
        return articleMapper.entityToDto(article);
    }


    @Override
    public void deleteArticleById(String id)
    {
        if(!articleRepository.existsById(id))
        {
            throw new ArticleServiceException("Record with provided id is not found");
        }
        articleRepository.deleteById(id);
    }

    public List<ArticleDTO> getAllArticles(){
        List<Article> articles = new LinkedList<Article>();
        articles = articleRepository.findAll();

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
