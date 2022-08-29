package com.softserve.edu.sporthubujp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softserve.edu.sporthubujp.entity.comment.LikeDislikeStatus;

public interface LikeDislikeStatusRepository extends JpaRepository<LikeDislikeStatus, String> {
    List<LikeDislikeStatus> findAllByUserId(String userId);
}
