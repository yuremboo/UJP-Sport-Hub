package com.softserve.edu.sporthubujp.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class TeamSaveDTO {
    private String name;
    private String location;
    private String alt;
    private String logo;
    private String description;
    private String categoryId;
}