package com.softserve.edu.sporthubujp.jwt;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class UsernameAndPasswordAuthenticationRequest {
    private String username;
    private String password;
}
