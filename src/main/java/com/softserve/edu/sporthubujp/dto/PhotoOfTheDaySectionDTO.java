package com.softserve.edu.sporthubujp.dto;

import com.softserve.edu.sporthubujp.validator.NameConstraint;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PhotoOfTheDaySectionDTO {

    @NotNull
    @NameConstraint
    String alt;

    @NotNull
    @NameConstraint
    String author;

    @NotNull
    @NameConstraint
    String title;

    @NotNull
    @NameConstraint
    String shortDescription;

}
