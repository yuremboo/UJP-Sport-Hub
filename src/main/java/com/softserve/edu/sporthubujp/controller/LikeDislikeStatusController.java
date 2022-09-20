package com.softserve.edu.sporthubujp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softserve.edu.sporthubujp.dto.comment.LikeDislikeStatusDTO;
import com.softserve.edu.sporthubujp.dto.comment.LikeDislikeStatusSaveDTO;
import com.softserve.edu.sporthubujp.service.LikeDislikeStatusService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v1")
public class LikeDislikeStatusController {
    private final LikeDislikeStatusService likeDislikeStatusService;

    @Autowired
    public LikeDislikeStatusController(LikeDislikeStatusService likeDislikeStatusService) {
        this.likeDislikeStatusService = likeDislikeStatusService;
    }

    @DeleteMapping("/like-dislike-statuses/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Void> deleteLikeDislikeStatus(@PathVariable("id") String id) {
        log.info("Delete like-dislike status by id {}", id);
        likeDislikeStatusService.deleteLikeDislikeStatus(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/like-dislike-statuses/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<LikeDislikeStatusSaveDTO> updateLikeDislikeStatus(@RequestBody LikeDislikeStatusSaveDTO newLikeDislikeStatus,
        @PathVariable("id") String id) {
        log.info("Update like-dislike status by id {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(
            likeDislikeStatusService.updateLikeDislikeStatus(newLikeDislikeStatus, id));
    }

    @PostMapping("/like-dislike-statuses")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<LikeDislikeStatusSaveDTO> addNewLikeDislikeStatus(@RequestBody LikeDislikeStatusSaveDTO newLikeDislikeStatus) {
        log.info("Add new user's like-dislike status to comment" + newLikeDislikeStatus.toString());
        return ResponseEntity.status(HttpStatus.OK).body(
            likeDislikeStatusService.addNewLikeDislikeStatus(newLikeDislikeStatus));
    }

    @GetMapping("/like-dislike-statuses/users/{userId}/comments/{commentId}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<LikeDislikeStatusDTO> getLikeDislikeStatusByUserIdAndCommentId(@PathVariable String userId,
        @PathVariable String commentId) {
        log.info("Get all like-dislike statuses by user id {} an comment id {}", userId, commentId);
        return ResponseEntity.status(HttpStatus.OK).body(
            likeDislikeStatusService.getStatusByUserIdAndCommentId(userId, commentId));
    }
}
