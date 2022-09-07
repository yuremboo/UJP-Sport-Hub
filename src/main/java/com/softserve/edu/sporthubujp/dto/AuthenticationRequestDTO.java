package com.softserve.edu.sporthubujp.dto;

import com.softserve.edu.sporthubujp.validator.EmailConstraint;
import com.softserve.edu.sporthubujp.validator.PasswordConstraint;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AuthenticationRequestDTO {
    @NotNull
    @EmailConstraint
    private String email;

    @NotNull
    @PasswordConstraint
    private String password;
}
