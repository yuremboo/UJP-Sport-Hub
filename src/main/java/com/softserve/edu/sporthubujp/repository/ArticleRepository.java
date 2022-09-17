package com.softserve.edu.sporthubujp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.sporthubujp.entity.Article;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends PagingAndSortingRepository<Article, String> {
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

    @Query(value = "SELECT * FROM ARTICLES a WHERE a.category_id = ?1",
            countQuery = "SELECT count(*) FROM ARTICLES a WHERE a.category_id = ?1",
            nativeQuery = true)
    Page<Article> findAllByCategoryId(String categoryId, Pageable pageable);

    @Query(value = "SELECT * FROM ARTICLES a WHERE a.category_id = ?1 AND a.is_active = ?2",
            countQuery = "SELECT count(*) FROM ARTICLES a WHERE a.category_id = ?1 AND a.is_active = ?2",
            nativeQuery = true)
    Page<Article> findAllByCategoryIdAndIsActive(String categoryId, boolean isActive, Pageable pageable);

    @Query("SELECT a FROM Article a "
            + "JOIN a.category c "
            + "WHERE c.id = ?1 AND a.isActive = true "
            + "ORDER BY a.createDateTime DESC ")
    Optional<List<Article>> findNewestArticlesByCategoryId(String categoryId, Pageable pageable);
}