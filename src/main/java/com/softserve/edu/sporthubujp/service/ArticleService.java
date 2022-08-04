package com.softserve.edu.sporthubujp.service;


import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import java.util.List;

public interface ArticleService {
    ArticleDTO getArticleById(String id);
    List<ArticleDTO> getAllArticles();
}
