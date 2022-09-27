package com.softserve.edu.sporthubujp.service;

import com.softserve.edu.sporthubujp.dto.RegistrationRequestDTO;

import javax.mail.SendFailedException;
import java.io.IOException;

public interface RegistrationService {
    /**
     * Method to register user and send a confirmation email letter
     *
     * @param request - an actual user registration data {@link RegistrationRequestDTO}
     * @return a String value of confirmation token {@link com.softserve.edu.sporthubujp.entity.ConfirmationToken}
     * @throws IOException
     * @throws SendFailedException
     */
    public String register(RegistrationRequestDTO request) throws IOException, SendFailedException;

    /**
     * Method to confirm a token
     *
     * @param token - a String value of confirmation token {@link com.softserve.edu.sporthubujp.entity.ConfirmationToken}
     * @return a route to frontend login page
     */
    public String confirmToken(String token);
}
