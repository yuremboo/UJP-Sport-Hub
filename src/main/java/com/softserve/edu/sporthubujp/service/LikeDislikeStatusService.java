package com.softserve.edu.sporthubujp.service;

import java.util.List;

import com.softserve.edu.sporthubujp.dto.comment.LikeDislikeStatusDTO;

public interface LikeDislikeStatusService {
    List<LikeDislikeStatusDTO> getAllStatusesByUserId(String userId);
    void deleteLikeDislikeStatus(String id);
    LikeDislikeStatusDTO updateLikeDislikeStatus(LikeDislikeStatusDTO newLDStatus, String id);
    LikeDislikeStatusDTO addNewLikeDislikeStatus(LikeDislikeStatusDTO newLDStatus);
}
