package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.dto.UserDTO;
import com.softserve.edu.sporthubujp.entity.User;
import com.softserve.edu.sporthubujp.mapper.UserMapper;
import com.softserve.edu.sporthubujp.entity.ConfirmationToken;
import com.softserve.edu.sporthubujp.repository.UserRepository;
import com.softserve.edu.sporthubujp.security.PasswordConfig;
import com.softserve.edu.sporthubujp.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordConfig passwordConfig;
    private final UserMapper userMapper;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public String signUpUser(UserDTO userDTO) {

        log.info(String.format("Service: signing up user with the email.html %s", userDTO.getEmail()));

        User user = userMapper.dtoToEntity(userDTO);
        boolean userExists = userRepository
                .findByEmail(user.getEmail())
                .isPresent();

        if (userExists) {

            log.error(String.format("Service: email.html %s already taken", userDTO.getEmail()));
            throw new IllegalStateException("email.html already taken");
        }

        String encodedPassword = passwordConfig.passwordEncoder()
                .encode(user.getPassword());

        user.setPassword(encodedPassword);
        user.setCreateDateTime(LocalDateTime.now());

        log.info(String.format("Service: saving user with the email.html %s", userDTO.getEmail()));
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
        log.debug(String.format("enabling user with the email.html %s", email));
        return userRepository.enableUser(email);
    }

}
