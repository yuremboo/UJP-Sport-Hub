package com.softserve.edu.sporthubujp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.softserve.edu.sporthubujp.dto.comment.CommentDTO;
import com.softserve.edu.sporthubujp.dto.comment.LikeDislikeStatusDTO;
import com.softserve.edu.sporthubujp.dto.comment.LikeDislikeStatusSaveDTO;
import com.softserve.edu.sporthubujp.entity.comment.Comment;
import com.softserve.edu.sporthubujp.entity.comment.LikeDislikeStatus;
import com.softserve.edu.sporthubujp.service.CommentService;
import com.softserve.edu.sporthubujp.service.LikeDislikeStatusService;
import com.softserve.edu.sporthubujp.service.UserService;

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

    @DeleteMapping("/likedislikestatuses/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Void> deleteLikeDislikeStatus(@PathVariable("id") String id) {
        log.info("Delete like-dislike status by id {}", id);
        likeDislikeStatusService.deleteLikeDislikeStatus(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/likedislikestatuses/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<LikeDislikeStatus> updateLikeDislikeStatus(@RequestBody LikeDislikeStatusDTO newLikeDislikeStatus,
        @PathVariable("id") String id) {
        log.info("Update like-dislike status by id {}", id);
        likeDislikeStatusService.updateLikeDislikeStatus(newLikeDislikeStatus, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(value = "/likedislikestatuses",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<LikeDislikeStatusSaveDTO> addNewLikeDislikeStatus(LikeDislikeStatusSaveDTO newLikeDislikeStatus) {
        log.info("Add new user's like-dislike status to comment" + newLikeDislikeStatus.toString());
        return ResponseEntity.status(HttpStatus.OK).body(likeDislikeStatusService.addNewLikeDislikeStatus(newLikeDislikeStatus));
    }

    @GetMapping("/{id}/likedislikestatuses")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<List<LikeDislikeStatusDTO>> getAllLikeDislikeStatusesByUserId(@PathVariable String id) {
        log.info("Get all like-dislike statuses by user id {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(
            likeDislikeStatusService.getAllStatusesByUserId(id));
    }
}
