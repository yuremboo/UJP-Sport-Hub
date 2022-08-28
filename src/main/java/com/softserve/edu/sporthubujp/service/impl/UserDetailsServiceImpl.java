package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.entity.Role;
import com.softserve.edu.sporthubujp.entity.User;
import com.softserve.edu.sporthubujp.exception.EmailNotConfirmedException;
import com.softserve.edu.sporthubujp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "Service: user with email %s not found";
    private static final String EMAIL_NOT_CONFIRMED = "Service: email %s not confirmed";
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info(String.format("Service: loading user with email %s", email));

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException(
                        String.format(USER_NOT_FOUND_MSG, email)));

        if (user.getIsActive() == null) {
            log.error(String.format(EMAIL_NOT_CONFIRMED, user.getEmail()));
            throw new EmailNotConfirmedException(
                    String.format(EMAIL_NOT_CONFIRMED, user.getEmail()), user);
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(String.valueOf(user.getRole()))
                .build();
    }
}
