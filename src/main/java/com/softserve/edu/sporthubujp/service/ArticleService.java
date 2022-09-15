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

    List<ArticleListDTO> getMorePopularArticles(Pageable pageable);

    List<ArticleDTO> getAllArticlesBySubscription(String idUser);

    List<ArticleListDTO> getArticlesByTeamByUserId(String idUser, String teamId);
    List<ArticleDTO> getAllArticlesByCategoryName(String nameCategory);
    ArticleDTO updateArticle(ArticleSaveDTO newArticle, String id);
    
    List<ArticleListDTO> getMostCommentedArticles();
    
    List<ArticleListDTO> getNewestArticlesByCategoryId(String categoryId, Pageable pageable);

    List<ArticleListDTO> getAllArticlesWithoutPagination();

    ArticleDTO publishUnpublishedArticle(String id);

}
