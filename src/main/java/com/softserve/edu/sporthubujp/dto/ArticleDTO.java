package com.softserve.edu.sporthubujp.dto;

import com.softserve.edu.sporthubujp.entity.Category;
import com.softserve.edu.sporthubujp.entity.Comment;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ArticleDTO {
    private String id;
    private String title;
    private String text;
    private Boolean isActive;
    private Boolean commentsActive;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    private CategoryDTO category;
}
