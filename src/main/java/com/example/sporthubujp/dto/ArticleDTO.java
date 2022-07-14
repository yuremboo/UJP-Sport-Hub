package com.example.sporthubujp.dto;

import lombok.*;

import java.time.LocalDateTime;
@Data
@EqualsAndHashCode
@ToString
public class ArticleDTO {
    private String id;
    private String title;
    private String aText;
    private Boolean isActive;
    private Boolean commentsActive;
    private String categoryId;
    private LocalDateTime publicationDateTime;
    private LocalDateTime updateDateTime;

    public ArticleDTO(String id, String title, String aText,
                      Boolean isActive, Boolean commentsActive,
                      String categoryId,
                      LocalDateTime publicationDateTime,
                      LocalDateTime updateDateTime) {
        this.id = id;
        this.title = title;
        this.aText = aText;
        this.isActive = isActive;
        this.commentsActive = commentsActive;
        this.categoryId = categoryId;
        this.publicationDateTime = publicationDateTime;
        this.updateDateTime = updateDateTime;
    }
}
