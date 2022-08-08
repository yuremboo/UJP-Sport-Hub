package com.softserve.edu.sporthubujp.controller;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/articles")

public class ArticleController {
    private static final Logger LOG = Logger.getLogger(ArticleController.class.getName());
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable String id) {
        LOG.log(Level.INFO, "Get article by id {0}", id);
        return ResponseEntity.status(HttpStatus.OK).body(
                articleService.getArticleById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") String articleId) {
        LOG.log(Level.INFO, "Delete article by id {0}", articleId);
        articleService.deleteArticleById(articleId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
