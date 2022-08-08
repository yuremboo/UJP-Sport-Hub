package com.softserve.edu.sporthubujp.repository;

import com.softserve.edu.sporthubujp.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, String> {
//    List<Article> findAllByCategoryId(String categoryId, Pageable pageable);

    List<Article> findAllByCategoryIdAndIsActive(String categoryId, boolean isActive, Pageable pageable);
}
