package com.softserve.edu.sporthubujp.service.impl;

import java.util.Objects;

import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import com.softserve.edu.sporthubujp.exception.ArticleServiceException;
import com.softserve.edu.sporthubujp.mapper.ArticleMapper;
import com.softserve.edu.sporthubujp.repository.ArticleRepository;
import com.softserve.edu.sporthubujp.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository,
                              ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    public ArticleDTO getArticleById(String id) {
        return articleMapper.entityToDto(articleRepository
                .getReferenceById(id));
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

    public Article updateArticle(Article newArticle, String id) {
        return articleRepository.findById(id)
            .map(article -> {
                articleMapper.updateArticle(article, newArticle);
                return articleRepository.save(article);
            })
            .orElseGet(() -> {
                newArticle.setId(id);
                return articleRepository.save(newArticle);
            });
    }
}
