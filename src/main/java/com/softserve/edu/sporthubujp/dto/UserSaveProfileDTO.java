package com.softserve.edu.sporthubujp.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.softserve.edu.sporthubujp.validator.EmailConstraint;
import com.softserve.edu.sporthubujp.validator.NameConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveProfileDTO {
    @NotNull
    @NameConstraint
    private String firstName;
    @NotNull
    @NameConstraint
    private String lastName;
    @NotNull
    @EmailConstraint
    private String email;
    private String photo;
    private LocalDateTime updateDateTime;
}
