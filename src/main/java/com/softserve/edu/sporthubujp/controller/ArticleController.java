package com.softserve.edu.sporthubujp.controller;

import java.util.List;
import com.softserve.edu.sporthubujp.dto.ArticleListDTO;
import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import com.softserve.edu.sporthubujp.service.ArticleService;
import org.mapstruct.MappingTarget;
import com.softserve.edu.sporthubujp.dto.CommentDTO;
import com.softserve.edu.sporthubujp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("api/v1")
public class ArticleController {
    private final ArticleService articleService;
    private final CommentService commentService;

    @Autowired
    public ArticleController(ArticleService articleService, CommentService commentService) {
        this.articleService = articleService;
        this.commentService = commentService;
    }


    @GetMapping("/articles/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable String id) {
        log.info("Get article by id {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(
            articleService.getArticleById(id));
    }

    @GetMapping("/{id}/comments")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<List<CommentDTO>> getAllCommentByArticleId(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(
            commentService.getAllCommentByArticleId(id));
    }
    @DeleteMapping(path = "/articles/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") String articleId) {
        log.info("Delete article by id {}", articleId);
        articleService.deleteArticleById(articleId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Article> updateArticle(@RequestBody Article newArticle,
        @PathVariable("id") String id) {
        articleService.updateArticle(newArticle, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/admin/articles")
    public ResponseEntity<List<ArticleDTO>> getAllArticles() {
        return ResponseEntity.status(HttpStatus.OK).body(
                articleService.getAllArticles());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/admin/articles/category_id/{id}")
    public ResponseEntity<List<ArticleListDTO>>
    getAllArticlesByCategoryId(@PathVariable String id, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(
                articleService.getAllArticlesByCategoryId(id,pageable));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/admin/articles/category_id/{id}/isactive/{isactive}")
    public ResponseEntity<List<ArticleListDTO>>
    getAllArticlesByCategoryIdAndIsActive(@PathVariable String id, @PathVariable boolean isactive, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(
                articleService.getAllArticlesByCategoryIdAndIsActive(id, isactive, pageable));
    }

//    @PreAuthorize("hasAnyAuthority('ADMIN')")
//    @GetMapping("/category_id")
//    public ResponseEntity<List<ArticleListDTO>> getAllArticlesByCategoryId(@RequestParam String id, @RequestParam boolean isactive, Pageable pageable) {
//        return ResponseEntity.status(HttpStatus.OK).body(
//                articleService.getAllArticlesByCategoryIdAndIsActive(id, isactive, pageable));
//    }

}
