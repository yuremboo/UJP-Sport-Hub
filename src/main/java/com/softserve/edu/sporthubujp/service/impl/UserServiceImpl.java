package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.dto.UserDTO;
import com.softserve.edu.sporthubujp.entity.User;
import com.softserve.edu.sporthubujp.mapper.UserMapper;
import com.softserve.edu.sporthubujp.registration.token.ConfirmationToken;
import com.softserve.edu.sporthubujp.registration.token.ConfirmationTokenService;
import com.softserve.edu.sporthubujp.repository.UserRepository;
import com.softserve.edu.sporthubujp.security.PasswordConfig;
import com.softserve.edu.sporthubujp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordConfig passwordConfig;
    private final UserMapper userMapper;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public String signUpUser(UserDTO userDTO) {

        User user = userMapper.dtoToEntity(userDTO);
        boolean userExists = userRepository
                .findByEmail(user.getEmail())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = passwordConfig.passwordEncoder()
                .encode(user.getPassword());

        user.setPassword(encodedPassword);
        user.setCreateDateTime(LocalDateTime.now());

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

//        TODO: SEND EMAIL

        return token;
    }

    @Override
    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }

}
