package com.softserve.edu.sporthubujp.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CommentDTO {
    private String id;
    private String comment;
    private Integer likes;
    private Integer dislikes;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
}
