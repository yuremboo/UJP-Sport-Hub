package com.softserve.edu.sporthubujp.dto.comment;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LikeDislikeStatusDTO {
    private String id;
    @NotNull
    private Boolean likedDisliked;
    private String commentId;
    private String userId;
}
