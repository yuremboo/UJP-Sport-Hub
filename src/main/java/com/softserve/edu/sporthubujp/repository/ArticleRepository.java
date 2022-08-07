package com.softserve.edu.sporthubujp.repository;

import com.softserve.edu.sporthubujp.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, String> {
}
