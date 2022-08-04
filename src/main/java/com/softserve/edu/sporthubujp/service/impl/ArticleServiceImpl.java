package com.softserve.edu.sporthubujp.service.impl;

import javax.persistence.EntityNotFoundException;

import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import com.softserve.edu.sporthubujp.mapper.ArticleMapper;
import com.softserve.edu.sporthubujp.repository.ArticleRepository;
import com.softserve.edu.sporthubujp.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throw new EntityNotFoundException(String.format(ARTICLE_NOT_FOUND_BY_ID, id));
        }
        return articleMapper.entityToDto(article);
    }
}
