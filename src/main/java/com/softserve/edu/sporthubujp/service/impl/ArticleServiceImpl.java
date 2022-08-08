package com.softserve.edu.sporthubujp.service.impl;

import java.util.logging.Logger;

import com.softserve.edu.sporthubujp.controller.ArticleController;
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
    private static final Logger LOG = Logger.getLogger(ArticleServiceImpl.class.getName());
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository,
                              ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    public ArticleDTO getArticleById(String id) {
        LOG.info("Get article by id in service");
        return articleMapper.entityToDto(articleRepository
                .getReferenceById(id));
    }


    @Override
    public void deleteArticleById(String id)
    {
        LOG.info("Delete article by id in service");
        if(!articleRepository.existsById(id))
        {
            //LOG.log(Level.WARNING,"Record with provided id is not found" );
            LOG.throwing(ArticleServiceImpl.class.getName(),"deleteArticleById",new ArticleServiceException("Record with provided id is not found"));
            throw new ArticleServiceException("Record with provided id is not found");
        }
        articleRepository.deleteById(id);
    }
}
