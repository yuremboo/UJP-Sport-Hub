package com.softserve.edu.sporthubujp.service.impl;

import com.google.common.io.Files;
import com.softserve.edu.sporthubujp.entity.User;
import com.softserve.edu.sporthubujp.exception.InvalidEmailException;
import com.softserve.edu.sporthubujp.mapper.UserMapper;
import com.softserve.edu.sporthubujp.repository.UserRepository;
import com.softserve.edu.sporthubujp.security.PasswordConfig;
import com.softserve.edu.sporthubujp.service.EmailSenderService;
import com.softserve.edu.sporthubujp.service.UserService;
import lombok.extern.slf4j.Slf4j;
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
            " url=http://localhost:3000/reset/password\" />";
    private final static String EMAIL_SERVER = "sportshubsmtp@gmail.com";
    private final static String USER_NOT_FOUND_MSG =
            "Incorrect user ID or password. Try again";
    private final PasswordConfig passwordConfig;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserService userService;
    private final EmailSenderService emailSender;

    public ForgotPasswordService(PasswordConfig passwordConfig, UserMapper userMapper, UserRepository userRepository, UserService userService, EmailSenderService emailSender) {
        this.passwordConfig = passwordConfig;
        this.userMapper = userMapper;
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

        String link = "http://localhost:3000/reset/password/"+newToken;
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

    public Void setNewPassword(String password, String token){
        log.info(String.format("Set new password"));
        User user = userRepository
                .findByPasswordResetToken(token)
                .orElseThrow(() -> new InternalAuthenticationServiceException(USER_NOT_FOUND_MSG));
        String encodedPassword = passwordConfig.passwordEncoder()
                .encode(password);
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return null;
    }
}
