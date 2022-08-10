package com.softserve.edu.sporthubujp.controller;

import java.util.List;

import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.dto.CommentDTO;
import com.softserve.edu.sporthubujp.service.ArticleService;
import com.softserve.edu.sporthubujp.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("api/v1/articles")

public class ArticleController {
    private final ArticleService articleService;
    private final CommentService commentService;

    @Autowired
    public ArticleController(ArticleService articleService, CommentService commentService) {
        this.articleService = articleService;
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(
            articleService.getArticleById(id));
    }
    @GetMapping("/{id}/comments")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<List<CommentDTO>> getAllCommentByArticleId(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(
            commentService.getAllCommentByArticleId(id));
    }
    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") String articleId)
    {
        articleService.deleteArticleById(articleId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
