package com.softserve.edu.sporthubujp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.sporthubujp.entity.comment.LikeDislikeStatus;

public interface LikeDislikeStatusRepository extends JpaRepository<LikeDislikeStatus, String> {

    @Transactional
    @Query("SELECT s FROM LikeDislikeStatus s "
        + "JOIN s.comment c "
        + "JOIN s.user u "
        + "WHERE u.id = ?1 AND c.id = ?2 ")
    LikeDislikeStatus findByUserIdAndCommentId(String userId, String commentId);
}
