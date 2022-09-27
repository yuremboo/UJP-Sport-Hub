package com.softserve.edu.sporthubujp.service;


import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.dto.ArticleListDTO;
import com.softserve.edu.sporthubujp.dto.ArticlePreviewDTO;
import com.softserve.edu.sporthubujp.dto.ArticleSaveDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ArticleService {
    ArticleDTO getArticleById(String id);

    Page<ArticleListDTO> getAllArticles(Pageable pageable);
    Page<ArticleListDTO> getAllArticlesByCategoryId(String categoryId, Pageable pageable);
    Page<ArticleListDTO> getAllArticlesByCategoryIdAndIsActive(String categoryId, boolean isActive, Pageable pageable);

    List<ArticlePreviewDTO> getSixActiveArticlesByCategoryId(String categoryId, String articleId);
    void deleteArticleById(String id);

    List<ArticlePreviewDTO> getMorePopularArticles(Pageable pageable);

    List<ArticleDTO> getAllArticlesBySubscription(String idUser);

    List<ArticleListDTO> getArticlesByTeamByUserId(String idUser, String teamId);
    List<ArticleDTO> getAllArticlesByCategoryName(String nameCategory);
    void selectedByAdminArticle(List<String> articleIDList);

    /**
     * The method allows to edit an existing article
     * @param newArticle an {@link ArticleSaveDTO} instance that contains new article values
     * @param id represents id of current article
     * @return instance of {@link ArticleDTO}
     */
    ArticleDTO updateArticle(ArticleSaveDTO newArticle, String id);
    
    List<ArticleListDTO> getMostCommentedArticles();
    
    List<ArticleListDTO> getNewestArticlesByCategoryId(String categoryId, Pageable pageable);

    List<ArticleListDTO> getAllArticlesByTeamId(String teamId);

    /**
     * The method allows to publish or unpublished article
     * @param id represents id of current article
     * @return instance of {@link ArticleDTO}
     */
    ArticleDTO publishUnpublishedArticle(String id);

    /**
     * The method allows to create an article
     * @param newArticle an {@link ArticleSaveDTO} instance that contains article values
     * @return instance of {@link ArticleSaveDTO}
     */
    ArticleSaveDTO postArticle(ArticleSaveDTO newArticle);

    List<ArticlePreviewDTO> getAllArticlesSelectedByAdmin();
}