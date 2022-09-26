package com.softserve.edu.sporthubujp.dto.comment;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentSaveDTO {
    private Integer likes;
    private Integer dislikes;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    @NotNull
    private String userId;
    @NotNull
    private String articleId;
    @NotNull
    private String commentText;
    @NotNull
    private Boolean isEdited;
}