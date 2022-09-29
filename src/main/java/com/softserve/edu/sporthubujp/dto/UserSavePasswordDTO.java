package com.softserve.edu.sporthubujp.dto;

import javax.validation.constraints.NotNull;

import com.softserve.edu.sporthubujp.validator.PasswordConstraint;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class UserSavePasswordDTO {
    @NotNull
    @PasswordConstraint
    private String password;
    @NotNull
    @PasswordConstraint
    private String oldPassword;
}