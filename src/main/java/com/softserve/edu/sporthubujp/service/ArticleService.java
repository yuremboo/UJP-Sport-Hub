package com.softserve.edu.sporthubujp.service;


import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.dto.ArticleSaveDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import com.softserve.edu.sporthubujp.dto.ArticleListDTO;
import org.springframework.data.domain.Pageable;
import java.util.List;


public interface ArticleService {
    ArticleDTO getArticleById(String id);
    
    List<ArticleDTO> getAllArticles();

    List<ArticleListDTO> getAllArticlesByCategoryId(String categoryId, Pageable pageable);

    List<ArticleListDTO> getAllArticlesByCategoryIdAndIsActive(String categoryId, boolean isActive, Pageable pageable);

    void deleteArticleById(String id);

    List<ArticleDTO> getAllArticlesBySubscription(String idUser);

    ArticleDTO updateArticle(ArticleSaveDTO newArticle, String id);
}
