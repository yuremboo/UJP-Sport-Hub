package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import com.softserve.edu.sporthubujp.mapper.ArticleMapper;
import com.softserve.edu.sporthubujp.repository.ArticleRepository;
import com.softserve.edu.sporthubujp.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<ArticleDTO> getAllArticles(){
        List<Article> articles = new LinkedList<Article>();
        articles = articleRepository.findAll();

        List<ArticleDTO> articleDTOS = new LinkedList<ArticleDTO>();
        for (var article : articles) {
            articleDTOS.add(articleMapper.entityToDto(article));
        }
        return articleDTOS;
    }
}
