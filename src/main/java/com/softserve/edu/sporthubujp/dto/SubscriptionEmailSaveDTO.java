package com.softserve.edu.sporthubujp.dto;

import javax.validation.constraints.NotNull;

import com.softserve.edu.sporthubujp.validator.EmailConstraint;

import lombok.Data;

@Data
public class SubscriptionEmailSaveDTO {
    @NotNull
    @EmailConstraint
    private String email;
}
