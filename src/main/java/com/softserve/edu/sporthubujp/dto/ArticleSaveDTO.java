package com.softserve.edu.sporthubujp.dto;

import javax.validation.constraints.NotNull;

import com.softserve.edu.sporthubujp.validator.NameConstraint;

import lombok.Data;

@Data
public class ArticleSaveDTO {
    private String id;
    @NotNull
    @NameConstraint
    private String title;
    @NotNull
    private String text;
    @NotNull
    @NameConstraint
    private String caption;
    @NotNull
    @NameConstraint
    private String alt;
    @NotNull
    private String location;
    @NotNull
    private String picture;
    @NotNull
    private Boolean isActive;
    private Boolean commentsActive;
    @NotNull
    private Boolean selectedByAdmin;
    @NotNull
    private String categoryId;
    @NotNull
    private String teamId;
}