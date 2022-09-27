package com.softserve.edu.sporthubujp.service.impl;

import com.google.common.io.Files;
import com.softserve.edu.sporthubujp.dto.RegistrationRequestDTO;
import com.softserve.edu.sporthubujp.dto.UserDTO;
import com.softserve.edu.sporthubujp.entity.Role;
import com.softserve.edu.sporthubujp.entity.ConfirmationToken;
import com.softserve.edu.sporthubujp.exception.InvalidPasswordException;
import com.softserve.edu.sporthubujp.exception.TokenAlreadyConfirmedException;
import com.softserve.edu.sporthubujp.exception.TokenExpiredException;
import com.softserve.edu.sporthubujp.exception.TokenNotFoundException;
import com.softserve.edu.sporthubujp.service.ConfirmationTokenService;
import com.softserve.edu.sporthubujp.service.EmailSenderService;
import com.softserve.edu.sporthubujp.service.RegistrationService;
import com.softserve.edu.sporthubujp.service.UserService;
import com.softserve.edu.sporthubujp.validator.PasswordValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.SendFailedException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

@Service
@AllArgsConstructor
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {
    private final static String TOKEN_NOT_FOUND = "Service: token %s not found";
    private final static String TOKEN_ALREADY_CONFIRMED = "Service: token %s is already confirmed";
    private final static String TOKEN_EXPIRED = "Service: token %s expired";
    private final static String INVALID_PASSWORD =
            "Service: password %s must contain at least 8 characters (letters and numbers)";

    private final static String LOGIN_ROUTE = "<meta http-equiv=\"refresh\" content=\"0;" +
            " url=http://localhost:3000/login\" />";
    private final static String EMAIL_SERVER = "sportshubsmtp@gmail.com";

    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenServiceImpl;
    private final EmailSenderService emailSender;
    private final PasswordValidator passwordValidator;

    @Override
    public String register(RegistrationRequestDTO request)
            throws IOException, SendFailedException {

        log.info(String.format("Service: registering user with email %s", request.getEmail()));

        boolean isValidPassword = passwordValidator.
                test(request.getPassword());

        if (!isValidPassword) {
            log.error(String.format(INVALID_PASSWORD, request.getPassword()));
            throw new InvalidPasswordException(String.format(INVALID_PASSWORD, request.getPassword()), request);
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
                EMAIL_SERVER,
                buildEmail(link));

        return token;
    }

    @Transactional
    @Override
    public String confirmToken(String token) {
        log.info(String.format("Service: confirming token %s", token));
        ConfirmationToken confirmationToken = confirmationTokenServiceImpl
                .getToken(token)
                .orElseThrow(() ->
                        new TokenNotFoundException(String.format(TOKEN_NOT_FOUND, token)));

        if (confirmationToken.getConfirmedAt() != null) {
            log.error(String.format(TOKEN_ALREADY_CONFIRMED, token));
            throw new TokenAlreadyConfirmedException(String.format(TOKEN_ALREADY_CONFIRMED, token), confirmationToken);
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            log.error(String.format(TOKEN_EXPIRED, token));
            throw new TokenExpiredException(String.format(TOKEN_EXPIRED, token), confirmationToken);
        }

        confirmationTokenServiceImpl.setConfirmedAt(token);
        userService.enableUser(
                confirmationToken.getUser().getEmail());

        return LOGIN_ROUTE;
    }

    private String buildEmail(String link) throws IOException {
        String date = "\n" + LocalDateTime.now().getMonth().getDisplayName(TextStyle.FULL, Locale.US)
                + " " + LocalDateTime.now().getDayOfMonth()
                + ", " + LocalDateTime.now().getYear();

        StringBuilder email = new StringBuilder(Files
                .asCharSource(new File("src/main/resources/templates/email.html"), StandardCharsets.UTF_8)
                .read());

        email
                .insert(email.indexOf("Hub") + 3, date)
                .insert(email.indexOf("href=\"\"") + 6, link);

        return email.toString();
    }
}
