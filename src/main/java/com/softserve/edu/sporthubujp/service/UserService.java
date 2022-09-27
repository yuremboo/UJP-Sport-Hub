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

    String signUpUser(UserDTO userDTO);

    int enableUser(String email);

    User findUserByEmail(String email);

    UserSaveProfileDTO findUserById(String userId);

//    User findUserByPasswordResetToken(String token);

    UserDTO updateUser(User oldUser, UserSaveProfileDTO newUser);

    UserDTO updatePassword(User oldPassword, UserSavePasswordDTO newPassword) throws ServiceException;

    UserDTO resetUserPassword(User user, String newPassword) throws IOException, SendFailedException, IOException, SendFailedException;

    UserDTO getUser(User user);

}