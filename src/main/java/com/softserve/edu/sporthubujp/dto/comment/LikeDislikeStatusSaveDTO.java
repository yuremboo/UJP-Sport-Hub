package com.softserve.edu.sporthubujp.dto.comment;

import lombok.Data;

@Data
public class LikeDislikeStatusSaveDTO {
    private Boolean likedDisliked;
    private String commentId;
    private String userId;

}
