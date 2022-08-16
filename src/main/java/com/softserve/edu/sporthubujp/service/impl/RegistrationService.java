package com.softserve.edu.sporthubujp.service.impl;

import com.google.common.io.Files;
import com.softserve.edu.sporthubujp.dto.RegistrationRequestDTO;
import com.softserve.edu.sporthubujp.dto.UserDTO;
import com.softserve.edu.sporthubujp.email.EmailValidator;
import com.softserve.edu.sporthubujp.entity.Role;
import com.softserve.edu.sporthubujp.entity.ConfirmationToken;
import com.softserve.edu.sporthubujp.service.EmailSenderService;
import com.softserve.edu.sporthubujp.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

@Service
@AllArgsConstructor
@Slf4j
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSenderService emailSender;

    public String register(RegistrationRequestDTO request) throws IOException {
        log.info(String.format("Service: registration user with email.html %s", request.getEmail()));
        boolean isValidEmail = emailValidator.
                test(request.getEmail());

        if (!isValidEmail) {
            log.error(String.format("Service: email.html %s is not valid", request.getEmail()));
            throw new IllegalStateException("email.html not valid");
        }

        String token = userService.signUpUser(
                new UserDTO(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        Role.USER)
        );

        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;

        emailSender.send(
                request.getEmail(),
                buildEmail(link));

        return token;
    }

    @Transactional
    public String confirmToken(String token) {
        log.info(String.format("Service: confirming token %s", token));
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            log.error(String.format("Service: token %s is already confirmed", token));
            throw new IllegalStateException("email.html already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            log.error(String.format("Service: token %s is expired", token));
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(
                confirmationToken.getUser().getEmail());
        return "Email confirmed";
    }

    private String buildEmail(String link) throws IOException {
        String date = "\n" + LocalDateTime.now().getMonth().getDisplayName(TextStyle.FULL , Locale.US)
                + " " + LocalDateTime.now().getDayOfMonth()
                + ", " + LocalDateTime.now().getYear();

        StringBuilder email = new StringBuilder(Files.asCharSource(new File("src/main/resources/templates/email.html"),
                StandardCharsets.UTF_8).read());

        email.insert(email.indexOf("Hub") + 3, date).insert(email.indexOf("href=\"\"") + 6, link);

        return email.toString();
    }
}