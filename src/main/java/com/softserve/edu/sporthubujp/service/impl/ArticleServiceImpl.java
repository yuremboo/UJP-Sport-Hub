package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.entity.Article;
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
        Article deleteArticleById = articleRepository.getReferenceById(id);
        if (deleteArticleById == null)
        {
            //throw new articleServiceException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());
        }
        articleRepository.delete(deleteArticleById);
    }
}
