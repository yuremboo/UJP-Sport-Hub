package com.softserve.edu.sporthubujp.repository;

import com.softserve.edu.sporthubujp.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, String> {
    @Transactional
    @Query("SELECT a FROM Article a "
        + "JOIN a.category c "
        + "JOIN c.subscriptions s "
        + "JOIN s.user u "
        + "WHERE u.id = ?1 "
        + "ORDER BY a.createDateTime ")
    List<Article> getAllArticlesBySubscription(String idUser);
    @Transactional
    @Query("SELECT a FROM Article a "
        + "JOIN a.team t "
        + "JOIN t.subscriptions s "
        + "JOIN s.user u "
        + "WHERE u.id = ?1 AND t.id = ?2 "
        + "ORDER BY a.createDateTime ")
    List<Article> getArticlesByTeamId(String idUser, String teamId);

    Page<Article> findAll(Pageable pageable);
    List<Article> findAllByCategoryId(String categoryId, Pageable pageable);

    List<Article> findAllByCategoryIdAndIsActive(String categoryId, boolean isActive, Pageable pageable);
}