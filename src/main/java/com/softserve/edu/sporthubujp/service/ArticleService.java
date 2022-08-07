package com.softserve.edu.sporthubujp.service;


import com.softserve.edu.sporthubujp.dto.ArticleDTO;

public interface ArticleService {
    ArticleDTO getArticleById(String id);

    void deleteArticleById(String id);
}
