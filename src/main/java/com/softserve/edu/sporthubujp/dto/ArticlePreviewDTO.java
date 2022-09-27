package com.softserve.edu.sporthubujp.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ArticlePreviewDTO {
    private String id;
    private String title;
    private String caption;
    private Boolean isActive;
    private CategoryDTO category;
    private LocalDateTime createDateTime;
}
