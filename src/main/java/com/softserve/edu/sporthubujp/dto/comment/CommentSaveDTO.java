package com.softserve.edu.sporthubujp.dto.comment;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CommentSaveDTO {
    private Integer likes;
    private Integer dislikes;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    private String commenterId;
    private String articleId;
    private String comment;
}