package com.softserve.edu.sporthubujp.dto.comment;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LikeDislikeStatusSaveDTO {
    @NotNull
    private Boolean likedDisliked;
    @NotNull
    private String commentId;
    @NotNull
    private String userId;

}
