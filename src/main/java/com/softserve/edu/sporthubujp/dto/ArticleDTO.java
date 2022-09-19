package com.softserve.edu.sporthubujp.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class ArticleDTO {
    private String id;
    private String title;
    private String text;
    private String caption;
    private String alt;
    private String location;
    private String picture;
    private Boolean isActive;
    private Boolean commentsActive;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    private String categoryId;
    private String teamId;
}