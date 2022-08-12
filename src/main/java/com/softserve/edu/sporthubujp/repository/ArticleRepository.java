package com.softserve.edu.sporthubujp.repository;

import com.softserve.edu.sporthubujp.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, String> {
    @Transactional
    @Modifying
    @Query("SELECT * FROM Article " +
        "JOIN Category ON Category.id = Article.category_id " +
        "JOIN Subscription ON Subscription.category_id = Category.id "+
        "WHERE Subscription.user_id = ?1 " +
        "ORDER BY Article.create_date_time ")
    List<Article> getAllArticlesBySubscription(String idUser);
    List<Article> findAllByCategoryId(String categoryId, Pageable pageable);

    List<Article> findAllByCategoryIdAndIsActive(String categoryId, boolean isActive, Pageable pageable);
}