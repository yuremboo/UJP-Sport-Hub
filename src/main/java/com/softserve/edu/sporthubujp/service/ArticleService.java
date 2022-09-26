package com.softserve.edu.sporthubujp.service;


import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.dto.ArticleListDTO;
import com.softserve.edu.sporthubujp.dto.ArticleSaveDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface ArticleService {
    ArticleDTO getArticleById(String id);
    
    List<ArticleListDTO> getAllArticles(Pageable pageable);

    List<ArticleListDTO> getAllArticlesByCategoryId(String categoryId, Pageable pageable);

    List<ArticleListDTO> getAllArticlesByCategoryIdAndIsActive(String categoryId, boolean isActive, Pageable pageable);

    void deleteArticleById(String id);

    List<ArticleListDTO> getMorePopularArticles();

    List<ArticleDTO> getAllArticlesBySubscription(String idUser);

    List<ArticleListDTO> getArticlesByTeamByUserId(String idUser, String teamId);

    ArticleDTO updateArticle(ArticleSaveDTO newArticle, String id);
    
    List<ArticleListDTO> getMostCommentedArticles();

    /**
     * Method for getting four newest {@link ArticleDTO} articles by category id
     *
     * @param categoryId - id of a category {@link com.softserve.edu.sporthubujp.entity.Category}
     * @param pageable - pageable object to set an article number
     * @return a list of {@link ArticleListDTO} articles
     */
    List<ArticleListDTO> getNewestArticlesByCategoryId(String categoryId, Pageable pageable);

    ArticleDTO publishUnpublishedArticle(String id);
}
