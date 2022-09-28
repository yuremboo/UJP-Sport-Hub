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

    /**
     * Method for getting six most recent active articles of the same category as
     * the current article.
     *
     * @param categoryId is id for category of the current article
     * @param articleId represents the current article id that should be excluded
     *                  from the list
     * @return list of {@link ArticleListDTO}
     */
    List<ArticleListDTO> getSixActiveArticlesByCategoryId(String categoryId, String articleId);
    void deleteArticleById(String id);

    List<ArticleListDTO> getMorePopularArticles(Pageable pageable);

    List<ArticleDTO> getAllArticlesBySubscription(String idUser);

    List<ArticleListDTO> getArticlesByTeamByUserId(String idUser, String teamId);
    List<ArticleDTO> getAllArticlesByCategoryName(String nameCategory);
    void selectArticleByAdmin(List<String> articleIDList);

    /**
     * The method allows to edit an existing article
     * @param newArticle an {@link ArticleSaveDTO} instance that contains new article values
     * @param id represents id of current article
     * @return instance of {@link ArticleDTO}
     */
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

    /**
     * Method to get all articles by team id {@link com.softserve.edu.sporthubujp.entity.Team}
     *
     * @param teamId - actual team id
     * @return a list of {@link ArticleListDTO} articles
     */
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

    List<ArticleListDTO> getAllArticlesSelectedByAdmin();
}