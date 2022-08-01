package com.softserve.edu.sporthubujp.dto;

import lombok.Data;
import java.time.LocalDateTime;
@Data
public class CategoryDTO {
    private String id;
    private String name;
    private String description;
    private Boolean isActive;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    private CategoryDTO parent;
}
