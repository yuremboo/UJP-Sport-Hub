package com.softserve.edu.sporthubujp.service;


import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.dto.ArticleListDTO;
import com.softserve.edu.sporthubujp.dto.ArticleSaveDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ArticleService {
    ArticleDTO getArticleById(String id);

    Page<ArticleListDTO> getAllArticles(Pageable pageable);
    Page<ArticleListDTO> getAllArticlesByCategoryId(String categoryId, Pageable pageable);
    Page<ArticleListDTO> getAllArticlesByCategoryIdAndIsActive(String categoryId, boolean isActive, Pageable pageable);

    List<ArticleListDTO> getSixActiveArticlesByCategoryId(String categoryId, String articleId);
    void deleteArticleById(String id);

    List<ArticleListDTO> getMorePopularArticles();

    List<ArticleDTO> getAllArticlesBySubscription(String idUser);

    List<ArticleListDTO> getArticlesByTeamByUserId(String idUser, String teamId);

    ArticleDTO updateArticle(ArticleSaveDTO newArticle, String id);
    
    List<ArticleListDTO> getMostCommentedArticles();
    
    List<ArticleListDTO> getNewestArticlesByCategoryId(String categoryId, Pageable pageable);

    List<ArticleListDTO> getAllArticlesByTeamId(String teamId);

    ArticleDTO publishUnpublishedArticle(String id);

    ArticleSaveDTO postArticle(ArticleSaveDTO newArticle);
}
