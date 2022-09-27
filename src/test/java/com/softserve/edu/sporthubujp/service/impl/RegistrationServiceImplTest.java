package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.dto.RegistrationRequestDTO;
import com.softserve.edu.sporthubujp.entity.ConfirmationToken;
import com.softserve.edu.sporthubujp.entity.User;
import com.softserve.edu.sporthubujp.exception.*;
import com.softserve.edu.sporthubujp.service.ConfirmationTokenService;
import com.softserve.edu.sporthubujp.service.EmailSenderService;
import com.softserve.edu.sporthubujp.service.UserService;
import com.softserve.edu.sporthubujp.validator.PasswordValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.mail.SendFailedException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceImplTest {

    private static String TOKEN_NOT_FOUND = "Service: token %s not found";
    private static String TOKEN_ALREADY_CONFIRMED = "Service: token %s is already confirmed";
    private static String TOKEN_EXPIRED = "Service: token %s expired";
    private static String INVALID_PASSWORD =
            "Service: password %s must contain at least 8 characters (letters and numbers)";

    @Mock
    private UserService userService;
    @Mock
    private ConfirmationTokenService confirmationTokenServiceImpl;
    @Mock
    private EmailSenderService emailSender;
    @Mock
    private PasswordValidator passwordValidator;
    @InjectMocks
    private RegistrationServiceImpl underTest;

    @Test
    void willThrowInvalidPasswordException() {

        RegistrationRequestDTO requestDTO = new RegistrationRequestDTO(
                "firstName",
                "lastName",
                "email@gmail.com",
                "b6fdgw%dA"
        );

        given(passwordValidator.test(anyString())).willReturn(false);

        assertThatThrownBy(() -> underTest.register(requestDTO))
                .isInstanceOf(InvalidPasswordException.class)
                .hasMessageContaining(String.format(INVALID_PASSWORD, requestDTO.getPassword()));

        verify(userService, never()).signUpUser(any());

    }

    @Test
    void canRegister() throws SendFailedException, IOException {
        RegistrationRequestDTO requestDTO = new RegistrationRequestDTO(
                "firstName",
                "lastName",
                "email@gmail.com",
                "b6fdgw%dA"
        );

        when(passwordValidator.test(anyString())).thenReturn(true);

        underTest.register(requestDTO);

        verify(userService).signUpUser(any());
        verify(emailSender).send(anyString(), anyString());
    }

    @Test
    void willThrowTokenNotFoundException() {

        ConfirmationToken confirmationToken = new ConfirmationToken(
                "42d81fba-7832-42d5-a387-8ef7988a240b",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                any(User.class)
        );

        lenient().when(confirmationTokenServiceImpl.getToken(confirmationToken.getToken()))
                .thenThrow(new TokenNotFoundException());

        assertThatThrownBy(() -> underTest.confirmToken(confirmationToken.getToken()))
                .isInstanceOf(TokenNotFoundException.class)
                .hasMessageContaining(String.format(TOKEN_NOT_FOUND, confirmationToken.getToken()));

        verify(userService, never()).enableUser(anyString());

    }

    @Test
    void willThrowTokenAlreadyConfirmedException() {

        ConfirmationToken confirmationToken = spy(new ConfirmationToken());

        given(confirmationTokenServiceImpl.getToken(confirmationToken.getToken()))
                .willReturn(Optional.of(confirmationToken));

        when(confirmationToken.getConfirmedAt()).thenReturn(LocalDateTime.now());

        assertThatThrownBy(() -> underTest.confirmToken(confirmationToken.getToken()))
                .isInstanceOf(TokenAlreadyConfirmedException.class)
                .hasMessageContaining(String.format(TOKEN_ALREADY_CONFIRMED, confirmationToken.getToken()));

        verify(userService, never()).enableUser(anyString());

    }

    @Test
    void willThrowTokenExpiredException() {

        ConfirmationToken confirmationToken = spy(new ConfirmationToken());

        given(confirmationTokenServiceImpl.getToken(confirmationToken.getToken()))
                .willReturn(Optional.of(confirmationToken));

        when(confirmationToken.getConfirmedAt()).thenReturn(null);
        when(confirmationToken.getExpiresAt()).thenReturn(LocalDateTime.now().minusMinutes(15));

        assertThatThrownBy(() -> underTest.confirmToken(confirmationToken.getToken()))
                .isInstanceOf(TokenExpiredException.class)
                .hasMessageContaining(String.format(TOKEN_EXPIRED, confirmationToken.getToken()));

        verify(userService, never()).enableUser(anyString());

    }

    @Test
    void canConfirmToken() {

        ConfirmationToken confirmationToken = spy(new ConfirmationToken());
        User user = spy(new User());

        given(confirmationTokenServiceImpl.getToken(confirmationToken.getToken()))
                .willReturn(Optional.of(confirmationToken));

        when(confirmationToken.getConfirmedAt()).thenReturn(null);
        when(confirmationToken.getExpiresAt()).thenReturn(LocalDateTime.now().plusMinutes(15));
        when(confirmationToken.getUser()).thenReturn(user);

        underTest.confirmToken(confirmationToken.getToken());

        verify(confirmationTokenServiceImpl).setConfirmedAt(confirmationToken.getToken());
        verify(userService).enableUser(user.getEmail());

    }
}