package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.entity.ConfirmationToken;
import com.softserve.edu.sporthubujp.entity.User;
import com.softserve.edu.sporthubujp.exception.EntityNotExistsException;
import com.softserve.edu.sporthubujp.repository.ConfirmationTokenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConfirmationTokenServiceTest {
    @Mock
    private ConfirmationTokenRepository confirmationTokenRepository;
    @InjectMocks
    private ConfirmationTokenService underTest;

    @Test
    void canSaveConfirmationToken() {

        ConfirmationToken confirmationToken = new ConfirmationToken(
                "42d81fba-7832-42d5-a387-8ef7988a240b",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                any(User.class)
        );

        underTest.saveConfirmationToken(confirmationToken);

        ArgumentCaptor<ConfirmationToken> tokenArgumentCaptor =
                ArgumentCaptor.forClass(ConfirmationToken.class);

        verify(confirmationTokenRepository)
                .save(tokenArgumentCaptor.capture());

        ConfirmationToken capturedToken = tokenArgumentCaptor.getValue();

        assertThat(capturedToken).isEqualTo(confirmationToken);
    }

    @Test
    void canGetToken() {
        ConfirmationToken confirmationToken = new ConfirmationToken(
                "42d81fba-7832-42d5-a387-8ef7988a240b",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                any(User.class)
        );

        underTest.getToken(confirmationToken.getToken());

        when(confirmationTokenRepository.findByToken(confirmationToken.getToken()))
                .thenReturn(Optional.of(confirmationToken));

        verify(confirmationTokenRepository).findByToken(confirmationToken.getToken());

        ConfirmationToken confirmationTokenTest = confirmationTokenRepository.findByToken(confirmationToken.getToken())
                .orElseThrow(EntityNotFoundException::new);

        assertThat(confirmationTokenTest).isEqualTo(confirmationToken);

    }

    @Test
    void canSetConfirmedAt() {

        ConfirmationToken confirmationToken = new ConfirmationToken(
                "42d81fba-7832-42d5-a387-8ef7988a240b",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                new User()
        );

        underTest.setConfirmedAt(confirmationToken.getToken());

        ArgumentCaptor<LocalDateTime> dateArgumentCaptor =
                ArgumentCaptor.forClass(LocalDateTime.class);

        verify(confirmationTokenRepository).updateConfirmedAt(
                eq(confirmationToken.getToken()),
                dateArgumentCaptor.capture()
        );

        LocalDateTime capturedDate = dateArgumentCaptor.getValue();

        assertThat(capturedDate).isEqualToIgnoringNanos(LocalDateTime.now());

    }
}