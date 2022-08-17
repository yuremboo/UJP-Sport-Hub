package com.softserve.edu.sporthubujp.service;

import com.softserve.edu.sporthubujp.dto.UserDTO;
import com.softserve.edu.sporthubujp.entity.User;

public interface UserService {

    String signUpUser(UserDTO userDTO);

    int enableUser(String email);

    String findUserByEmail(String email);
}
