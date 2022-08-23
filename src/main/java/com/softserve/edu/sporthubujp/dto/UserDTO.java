package com.softserve.edu.sporthubujp.dto;

import com.softserve.edu.sporthubujp.entity.Role;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class UserDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String password;
    private String photo;
    private Boolean isActive;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;

    public UserDTO(String firstName,
                   String lastName,
                   String email,
                   String password,
                   Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
