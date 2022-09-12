package com.softserve.edu.sporthubujp.dto;

import com.softserve.edu.sporthubujp.validator.EmailConstraint;
import com.softserve.edu.sporthubujp.validator.NameConstraint;
import com.softserve.edu.sporthubujp.validator.PasswordConstraint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequestDTO {
    @NotNull
    @NameConstraint
    private final String firstName;

    @NotNull
    @NameConstraint
    private final String lastName;

    @NotNull
    @EmailConstraint
    private final String email;

    @NotNull
    @PasswordConstraint
    private final String password;
}