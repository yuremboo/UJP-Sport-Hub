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

import com.softserve.edu.sporthubujp.dto.comment.CommentDTO;
import com.softserve.edu.sporthubujp.dto.comment.CommentSaveDTO;
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
    public ResponseEntity<CommentSaveDTO> updateComment(@RequestBody CommentSaveDTO newComment,
        @PathVariable("id") String id) {
        log.info("Update comment by id {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(
            commentService.updateComment(newComment, id));
    }

    @PostMapping("/comments")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<CommentSaveDTO> addNewComment(@RequestBody CommentSaveDTO newComment) {
        log.info(String.format("Add new comment to article %s", newComment));
        return ResponseEntity.status(HttpStatus.OK).body(commentService.addNewComment(newComment));
    }
}
