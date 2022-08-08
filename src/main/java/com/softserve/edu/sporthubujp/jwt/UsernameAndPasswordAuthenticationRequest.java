package com.softserve.edu.sporthubujp.jwt;

import lombok.Data;

@Data
public class UsernameAndPasswordAuthenticationRequest {
    private String email;
    private String password;
}
