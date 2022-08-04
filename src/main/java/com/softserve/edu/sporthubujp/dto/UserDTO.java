package com.softserve.edu.sporthubujp.dto;

import com.softserve.edu.sporthubujp.entity.Role;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String password;
    private Boolean isActive;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
}
