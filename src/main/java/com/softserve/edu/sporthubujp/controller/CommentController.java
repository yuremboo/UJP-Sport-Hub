package com.softserve.edu.sporthubujp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softserve.edu.sporthubujp.entity.comment.Comment;
import com.softserve.edu.sporthubujp.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v1")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @DeleteMapping("/comments/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") String commentId) {
        log.info("Delete comment by id {}", commentId);
        commentService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/comments/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Comment> updateArticle(@RequestBody Comment newComment,
        @PathVariable("id") String id) {
        log.info("Update comment by id {}", id);
        commentService.updateComment(newComment, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/comments")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<Comment> addNewComment(Comment newComment) {
        log.info("Add new comment to article with id{}", newComment.getArticle().getId());
        commentService.addNewComment(newComment);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
