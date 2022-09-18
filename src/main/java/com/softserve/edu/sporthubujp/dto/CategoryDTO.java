package com.softserve.edu.sporthubujp.dto;

import com.softserve.edu.sporthubujp.entity.CategoryType;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class CategoryDTO {
    private String id;
    private String name;
    private CategoryType categoryType;
    private String description;
    private Boolean isActive;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    private CategoryDTO parent;
}
