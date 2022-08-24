package com.softserve.edu.sporthubujp.service;

import com.softserve.edu.sporthubujp.dto.UserDTO;
import com.softserve.edu.sporthubujp.dto.UserSaveProfileDTO;

public interface UserService {

    String signUpUser(UserDTO userDTO);

    int enableUser(String email);

    String findUserByEmail(String email);

    UserDTO updateUser(UserSaveProfileDTO newUser, String id);
}
