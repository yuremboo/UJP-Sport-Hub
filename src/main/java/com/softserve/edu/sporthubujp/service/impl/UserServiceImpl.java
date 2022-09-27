package com.softserve.edu.sporthubujp.service.impl;

import com.google.common.io.Files;
import com.softserve.edu.sporthubujp.dto.UserDTO;
import com.softserve.edu.sporthubujp.dto.UserSavePasswordDTO;
import com.softserve.edu.sporthubujp.dto.UserSaveProfileDTO;
import com.softserve.edu.sporthubujp.entity.ConfirmationToken;
import com.softserve.edu.sporthubujp.entity.User;
import com.softserve.edu.sporthubujp.exception.EmailAlreadyTakenException;
import com.softserve.edu.sporthubujp.exception.EntityNotExistsException;
import com.softserve.edu.sporthubujp.mapper.UserMapper;
import com.softserve.edu.sporthubujp.repository.UserRepository;
import com.softserve.edu.sporthubujp.security.PasswordConfig;
import com.softserve.edu.sporthubujp.service.EmailSenderService;
import com.softserve.edu.sporthubujp.service.UserService;
import com.softserve.edu.sporthubujp.validator.PasswordValidator;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.snowflake.client.jdbc.internal.google.protobuf.ServiceException;

import org.springframework.stereotype.Service;

import javax.mail.SendFailedException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.InvalidPropertiesFormatException;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final static String EMAIL_ALREADY_TAKEN = "Service: email %s already taken";
    private final String USER_NOT_FOUND_BY_ID = "User not found by id: %s";
    private final static String EMAIL_SERVER = "sportshubsmtp@gmail.com";

    private final UserRepository userRepository;
    private final PasswordConfig passwordConfig;
    private final UserMapper userMapper;
    private final ConfirmationTokenService confirmationTokenService;
    private final PasswordValidator passwordValidator;

    private final EmailSenderService emailSender;

    @Override
    public String signUpUser(UserDTO userDTO) {

        log.info(String.format("Service: signing up user with the email %s", userDTO.getEmail()));

        User user = userMapper.dtoToEntity(userDTO);
        boolean userExists = userRepository
            .findByEmail(userDTO.getEmail())
            .isPresent();

        if (userExists) {
            log.error(String.format(EMAIL_ALREADY_TAKEN, userDTO.getEmail()));
            throw new EmailAlreadyTakenException(String.format(EMAIL_ALREADY_TAKEN, userDTO.getEmail()), userDTO);
        }

        String encodedPassword = passwordConfig.passwordEncoder()
            .encode(userDTO.getPassword());

        user.setPassword(encodedPassword);
        user.setCreateDateTime(LocalDateTime.now());

        log.info(String.format("Service: saving user with the email %s", userDTO.getEmail()));
        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
            token,
            LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(15),
            user
        );

        confirmationTokenService.saveConfirmationToken(
            confirmationToken);

        return token;
    }

    @Override
    public int enableUser(String email) {
        log.debug(String.format("enabling user with the email %s", email));
        return userRepository.enableUser(email);
    }

    @Override
    public User findUserByEmail(String email) {
        log.info(String.format("find user with the email %s", email));
        return userRepository.findByEmail(email).
            orElseThrow(EntityNotExistsException::new);
    }

    @Override
    public UserSaveProfileDTO findUserById(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotExistsException();
        }
        log.info(String.format("find user with the id %s", userId));
        return userMapper.userToUserSaveDto(userRepository.findUserById(userId));
    }

    //    @Override
    //    public User findUserByPasswordResetToken(String token) {
    //        return null;
    //    }

    public UserDTO updateUser(User oldUser, UserSaveProfileDTO newUser) {

        boolean isPresentButMe = Objects.equals(oldUser.getEmail(), newUser.getEmail());

        if (!isPresentButMe) {
            userRepository.findByEmail(newUser.getEmail()).ifPresent(
                user -> {
                    throw new EmailAlreadyTakenException(
                        String.format(EMAIL_ALREADY_TAKEN, userMapper.dtoToSaveDto(newUser).getEmail()),
                        userMapper.dtoToSaveDto(newUser));
                }
            );
        }

        oldUser.setUpdateDateTime(LocalDateTime.now());

        return userMapper.entityToDto(userRepository.save(
            userMapper.updateUser(oldUser, newUser))
        );
    }
    public UserDTO updatePassword(User oldPassword, UserSavePasswordDTO newPassword)
        throws ServiceException {
        boolean checkPasswords = passwordConfig.passwordEncoder().matches(newPassword.getOldPassword(), oldPassword.getPassword());
        if (checkPasswords) {
            if (passwordValidator.test(newPassword.getPassword())) {
                newPassword.setPassword(passwordConfig.passwordEncoder().encode(newPassword.getPassword()));
            } else {
                throw new ServiceException("Service: password must contain at least 8 characters (letters and numbers)");
            }
        } else {
            throw new ServiceException("Service: old password not matches with entered password ");
        }
        return userRepository.findById(oldPassword.getId())
            .map(user -> {
                userMapper.updatePassword(user, newPassword);
                return userMapper.entityToDto(userRepository.save(user));
            })
            .orElseThrow(EntityNotExistsException::new);
    }

    @Override
    public UserDTO resetUserPassword(User user, String newPassword) throws IOException, SendFailedException {
        String link = "http://localhost:8080/api/v1/forgot/password";
        emailSender.sendCheckEmail(
            EMAIL_SERVER,
            buildConfirmEmail(link));

        String encodedPassword = passwordConfig.passwordEncoder()
            .encode(newPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return userMapper.entityToDto(user);
    }

    String buildConfirmEmail(String link) throws IOException {
        String date = "\n" + LocalDateTime.now().getMonth().getDisplayName(TextStyle.FULL, Locale.US)
            + " " + LocalDateTime.now().getDayOfMonth()
            + ", " + LocalDateTime.now().getYear();

        StringBuilder email = new StringBuilder(Files
            .asCharSource(new File("src/main/resources/templates/checkEmail.html"), StandardCharsets.UTF_8)
            .read());

        email
            .insert(email.indexOf("password") + 8, date)
            .insert(email.indexOf("href=\"\"") + 6, link);

        return email.toString();
    }

    public UserDTO getUser(User oldUser) {
        User user = userRepository.getReferenceById(oldUser.getId());
        return userMapper.entityToDto(user);
    }
}