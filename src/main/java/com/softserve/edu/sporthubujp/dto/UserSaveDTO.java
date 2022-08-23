package com.softserve.edu.sporthubujp.dto;

import lombok.Data;

@Data
public class UserSaveDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String photo;
}
