package com.softserve.edu.sporthubujp.service;


import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.entity.Article;

public interface ArticleService {
    ArticleDTO getArticleById(String id);
    void deleteArticleById(String id);

    Article updateArticle(Article newArticle, String id);
}
