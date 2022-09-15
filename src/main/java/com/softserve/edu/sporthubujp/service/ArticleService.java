package com.softserve.edu.sporthubujp.service;


import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.dto.ArticleListDTO;
import com.softserve.edu.sporthubujp.dto.ArticleSaveDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ArticleService {
    ArticleDTO getArticleById(String id);
    
    List<ArticleListDTO> getAllArticles(Pageable pageable);
    List<ArticleListDTO> getAllArticles2(Pageable pageable);
    Page<ArticleListDTO> getAllArticles3(Pageable pageable);

//    List<ArticleListDTO> getAllArticlesByCategoryId(String categoryId, Pageable pageable);
    Page<ArticleListDTO> getAllArticlesByCategoryId2(String categoryId, Pageable pageable);

//    List<ArticleListDTO> getAllArticlesByCategoryIdAndIsActive(String categoryId, boolean isActive, Pageable pageable);
    Page<ArticleListDTO> getAllArticlesByCategoryIdAndIsActive2(String categoryId, boolean isActive, Pageable pageable);

    void deleteArticleById(String id);

    List<ArticleListDTO> getMorePopularArticles();

    List<ArticleDTO> getAllArticlesBySubscription(String idUser);

    List<ArticleListDTO> getArticlesByTeamByUserId(String idUser, String teamId);

    ArticleDTO updateArticle(ArticleSaveDTO newArticle, String id);
    
    List<ArticleListDTO> getMostCommentedArticles();
    
    List<ArticleListDTO> getNewestArticlesByCategoryId(String categoryId, Pageable pageable);

//    List<ArticleListDTO> getAllArticlesWithoutPagination();

    ArticleDTO publishUnpublishedArticle(String id);

}
