package com.softserve.edu.sporthubujp.service;

import com.softserve.edu.sporthubujp.dto.UserDTO;
import com.softserve.edu.sporthubujp.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    public String signUpUser(UserDTO userDTO);
    public int enableUser(String email);
//    UserDTO selectUserByEmail(String email);
}
