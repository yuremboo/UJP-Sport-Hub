package com.softserve.edu.sporthubujp.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class UserSavePasswordDTO {
    private String password;
}