package com.softserve.edu.sporthubujp.service;

import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.dto.ArticleSaveDTO;
import com.softserve.edu.sporthubujp.dto.UserDTO;
import com.softserve.edu.sporthubujp.dto.UserSaveProfileDTO;
import com.softserve.edu.sporthubujp.entity.User;

import javax.mail.SendFailedException;
import java.io.IOException;

public interface UserService {

    String signUpUser(UserDTO userDTO);

    int enableUser(String email);

    User findUserByEmail(String email);

    UserSaveProfileDTO findUserById(String userId);

//    User findUserByPasswordResetToken(String token);

    /**
     * The method allows to edit an existing user
     * @param oldUser represents current user
     * @param newUser an {@link UserSaveProfileDTO} instance that contains new user values
     * @return instance of {@link UserDTO}
     */
    UserDTO updateUser(User oldUser, UserSaveProfileDTO newUser);

    UserDTO resetUserPassword(User user, String newPassword) throws IOException, SendFailedException, IOException, SendFailedException;

    /**
     * The method allows to get the current user
     * @param user represents current user
     * @return instance of {@link UserDTO}
     */
    UserDTO getUser(User user);
}