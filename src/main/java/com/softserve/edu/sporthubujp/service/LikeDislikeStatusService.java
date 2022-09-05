package com.softserve.edu.sporthubujp.service;

import java.util.List;

import com.softserve.edu.sporthubujp.dto.comment.LikeDislikeStatusDTO;
import com.softserve.edu.sporthubujp.dto.comment.LikeDislikeStatusSaveDTO;

public interface LikeDislikeStatusService {
    List<LikeDislikeStatusDTO> getAllStatusesByUserId(String userId);
    void deleteLikeDislikeStatus(String id);
    LikeDislikeStatusSaveDTO updateLikeDislikeStatus(LikeDislikeStatusSaveDTO newLDStatus, String id);
    LikeDislikeStatusSaveDTO addNewLikeDislikeStatus(LikeDislikeStatusSaveDTO newLDStatus);
}
