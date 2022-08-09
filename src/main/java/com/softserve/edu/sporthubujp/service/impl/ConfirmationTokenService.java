package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.entity.ConfirmationToken;
import com.softserve.edu.sporthubujp.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        log.info(String.format("Service: saving token %s", token.getToken()));
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        log.debug(String.format("Service: getting token %s", token));
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        log.debug(String.format("Service: confirming token %s", token));
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
