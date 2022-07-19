package com.softserve.edu.sporthubujp.dto;

import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor
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
}
