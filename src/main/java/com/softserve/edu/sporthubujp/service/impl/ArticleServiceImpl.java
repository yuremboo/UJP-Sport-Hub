package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.dto.ArticleListDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import com.softserve.edu.sporthubujp.mapper.ArticleMapper;
import com.softserve.edu.sporthubujp.repository.ArticleRepository;
import com.softserve.edu.sporthubujp.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

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
