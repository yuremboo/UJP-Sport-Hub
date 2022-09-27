package com.softserve.edu.sporthubujp.service;

import com.softserve.edu.sporthubujp.entity.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationTokenService {
    /**
     * Method for saving a confirmation token {@link ConfirmationToken} into the database
     *
     * @param token - an actual confirmation token object
     */
    public void saveConfirmationToken(ConfirmationToken token);

    /**
     * Method for getting a confirmation token from the database by its token String value
     *
     * @param token - token String value
     * @return an actual {@link ConfirmationToken} object
     */
    public Optional<ConfirmationToken> getToken(String token);

    /**
     * Method for setting confirmedAt field of {@link ConfirmationToken} object
     *
     * @param token - token String value
     */
    public void setConfirmedAt(String token);
}
