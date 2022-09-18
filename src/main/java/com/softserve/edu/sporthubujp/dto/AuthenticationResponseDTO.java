package com.softserve.edu.sporthubujp.dto;

import com.softserve.edu.sporthubujp.entity.Role;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AuthenticationResponseDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String jwt;
}