package com.softserve.edu.sporthubujp.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.softserve.edu.sporthubujp.validator.NameConstraint;

import lombok.Data;
@Data
public class TeamSaveDTO {
    @NotNull
    @NameConstraint
    private String name;
    @NotNull
    @NameConstraint
    private String location;
    @NotNull
    private String alt;
    @NotNull
    private String logo;
    @NotNull
    private String description;
    @NotNull
    private String categoryId;
}