package com.softserve.edu.sporthubujp.service;

import com.softserve.edu.sporthubujp.dto.UserDTO;

public interface UserService {

    String signUpUser(UserDTO userDTO);

    int enableUser(String email);
}
