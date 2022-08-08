package com.softserve.edu.sporthubujp.controller;

import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.dto.ArticleListDTO;
import com.softserve.edu.sporthubujp.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                articleService.getArticleById(id));
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
