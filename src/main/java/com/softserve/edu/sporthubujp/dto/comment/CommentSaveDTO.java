package com.softserve.edu.sporthubujp.dto.comment;

import java.time.LocalDateTime;

import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.dto.UserDTO;

import lombok.Data;

@Data
public class CommentSaveDTO {
    private Integer likes;
    private Integer dislikes;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    private String userId;
    private String articleId;
    private String commentText;
}