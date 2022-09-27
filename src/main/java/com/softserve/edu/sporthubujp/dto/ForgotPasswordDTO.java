package com.softserve.edu.sporthubujp.dto;

import com.softserve.edu.sporthubujp.validator.PasswordConstraint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ForgotPasswordDTO {
    @NotNull
    @PasswordConstraint
    private final String password;

    @NotNull
    private final String token;
}