package com.softserve.edu.sporthubujp.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TeamDTO {
    private String id;
    private String name;
    private String alt;
    private String location;
    private String logo;
    private String description;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    private CategoryDTO category;
}