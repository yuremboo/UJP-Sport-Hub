package com.softserve.edu.sporthubujp.service;

import java.util.List;

import com.softserve.edu.sporthubujp.dto.comment.LikeDislikeStatusDTO;
import com.softserve.edu.sporthubujp.dto.comment.LikeDislikeStatusSaveDTO;

public interface LikeDislikeStatusService {
    /**
     * Method for getting user's reaction to the comment
     * (like or dislike)
     *
     * @param userId is an id of the current user
     * @param commentId is an id of the current comment
     * @return instance of {@link LikeDislikeStatusDTO}
     */
    LikeDislikeStatusDTO getStatusByUserIdAndCommentId(String userId, String commentId);
    void deleteLikeDislikeStatus(String id);
    LikeDislikeStatusSaveDTO updateLikeDislikeStatus(LikeDislikeStatusSaveDTO newLDStatus, String id);
    LikeDislikeStatusSaveDTO addNewLikeDislikeStatus(LikeDislikeStatusSaveDTO newLDStatus);
}
