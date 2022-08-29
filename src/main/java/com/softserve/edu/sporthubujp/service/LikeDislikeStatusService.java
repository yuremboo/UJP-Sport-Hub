package com.softserve.edu.sporthubujp.service;

import java.util.List;

import com.softserve.edu.sporthubujp.dto.comment.LikeDislikeStatusDTO;
import com.softserve.edu.sporthubujp.entity.comment.LikeDislikeStatus;

public interface LikeDislikeStatusService {
    List<LikeDislikeStatusDTO> getAllStatusesByUserId(String userId);
    void deleteLikeDislikeStatus(String id);
    LikeDislikeStatus updateLikeDislikeStatus(LikeDislikeStatusDTO newLDStatus, String id);
    LikeDislikeStatus addNewLikeDislikeStatus(LikeDislikeStatusDTO newLDStatus);
}
