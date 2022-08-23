package com.softserve.edu.sporthubujp.service;

import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.dto.ArticleSaveDTO;
import com.softserve.edu.sporthubujp.dto.UserDTO;
import com.softserve.edu.sporthubujp.dto.UserSaveDTO;
import com.softserve.edu.sporthubujp.entity.User;

public interface UserService {

    String signUpUser(UserDTO userDTO);

    int enableUser(String email);

    String findUserByEmail(String email);

    UserDTO updateUser(UserSaveDTO newUser, String id);
}
