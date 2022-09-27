package com.softserve.edu.sporthubujp.service;

import com.softserve.edu.sporthubujp.dto.UserDTO;
import com.softserve.edu.sporthubujp.dto.UserSavePasswordDTO;
import com.softserve.edu.sporthubujp.entity.User;
import com.softserve.edu.sporthubujp.dto.UserSaveProfileDTO;
import javax.mail.SendFailedException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;

import net.snowflake.client.jdbc.internal.google.protobuf.ServiceException;

public interface UserService {

    /**
     * Method for saving user {@link User} into the database and
     * sending a token {@link com.softserve.edu.sporthubujp.entity.ConfirmationToken} to confirm
     *
     * @param userDTO - an actual user object of {@link UserDTO} type
     * @return generated confirmation token String
     */
    String signUpUser(UserDTO userDTO);

    /**
     * Method for enabling user and allowing him to use the web application
     *
     * @param email - an email of user
     */
    void enableUser(String email);

    User findUserByEmail(String email);

    UserSaveProfileDTO findUserById(String userId);

//    User findUserByPasswordResetToken(String token);

    UserDTO updateUser(User oldUser, UserSaveProfileDTO newUser);

    UserDTO updatePassword(User oldPassword, UserSavePasswordDTO newPassword) throws ServiceException;

    UserDTO resetUserPassword(User user, String newPassword) throws IOException, SendFailedException, IOException, SendFailedException;

    UserDTO getUser(User user);

}