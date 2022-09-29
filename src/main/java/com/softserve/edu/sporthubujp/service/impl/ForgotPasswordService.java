package com.softserve.edu.sporthubujp.service.impl;

import com.google.common.io.Files;
import com.softserve.edu.sporthubujp.entity.User;
import com.softserve.edu.sporthubujp.exception.InvalidEmailException;
import com.softserve.edu.sporthubujp.repository.UserRepository;
import com.softserve.edu.sporthubujp.security.PasswordConfig;
import com.softserve.edu.sporthubujp.service.EmailSenderService;
import com.softserve.edu.sporthubujp.service.UserService;
import com.softserve.edu.sporthubujp.validator.PasswordValidator;

import lombok.extern.slf4j.Slf4j;
import net.snowflake.client.jdbc.internal.google.protobuf.ServiceException;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Service;

import javax.mail.SendFailedException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.UUID;

@Service
@Slf4j
public class ForgotPasswordService {
    private final static String RESET_PASSWORD_ROUTE = "<meta http-equiv=\"refresh\" content=\"0;" +
            " url=https://ujp-sports-hub-ui.herokuapp.com/reset/password\" />";
    private final static String EMAIL_SERVER = "sportshubsmtp@gmail.com";
    private final static String USER_NOT_FOUND_MSG =
            "Incorrect user ID or password. Try again";
    private final PasswordConfig passwordConfig;
    private final PasswordValidator passwordValidator;
    private final UserRepository userRepository;
    private final UserService userService;
    private final EmailSenderService emailSender;

    public ForgotPasswordService(PasswordConfig passwordConfig, PasswordValidator passwordValidator, UserRepository userRepository, UserService userService, EmailSenderService emailSender) {
        this.passwordConfig = passwordConfig;
        this.passwordValidator = passwordValidator;
        this.userRepository = userRepository;
        this.userService = userService;
        this.emailSender = emailSender;
    }

    public Void resetPassword(String email) throws IOException, SendFailedException {
        User user = userService.findUserByEmail(email);
        if (user == null) {
            throw new InvalidEmailException();
        }
        String newToken = UUID.randomUUID().toString();
        user.setPasswordResetToken(newToken);
        userRepository.save(user);

        String link = "https://ujp-sports-hub-ui.herokuapp.com/reset/password/"+newToken;
        emailSender.send(
                user.getEmail(),
                buildEmail(link));
        return null;

    }

    private String buildEmail(String link) throws IOException {
        String date = "\n" + LocalDateTime.now().getMonth().getDisplayName(TextStyle.FULL , Locale.US)
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

    public Void setNewPassword(String password, String token) throws ServiceException {
        log.info(String.format("Set new password"));
        User user = userRepository
                .findByPasswordResetToken(token)
                .orElseThrow(() -> new InternalAuthenticationServiceException(USER_NOT_FOUND_MSG));
        String encodedPassword = passwordConfig.passwordEncoder()
                .encode(password);
        if (passwordValidator.test(encodedPassword)) {
            user.setPassword(encodedPassword);
        } else {
            throw new ServiceException("Service: password must contain at least 8 characters (letters and numbers)");
        }
        userRepository.save(user);
        return null;
    }
}
