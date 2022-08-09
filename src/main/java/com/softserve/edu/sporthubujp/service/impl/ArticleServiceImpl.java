package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.sporthubujp.entity.Article;
import com.softserve.edu.sporthubujp.exception.EntityNotExistsException;
import com.softserve.edu.sporthubujp.exception.ArticleServiceException;
import com.softserve.edu.sporthubujp.mapper.ArticleMapper;
import com.softserve.edu.sporthubujp.repository.ArticleRepository;
import com.softserve.edu.sporthubujp.service.ArticleService;
import lombok.extern.slf4j.Slf4j;

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
}
