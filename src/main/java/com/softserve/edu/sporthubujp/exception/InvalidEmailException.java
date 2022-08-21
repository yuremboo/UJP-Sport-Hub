package com.softserve.edu.sporthubujp.exception;

import com.softserve.edu.sporthubujp.dto.RegistrationRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.InvalidObjectException;

@Getter
@Setter
public class InvalidEmailException extends InvalidObjectException {
    private static final String INVALID_EMAIL = "email not valid";
    private RegistrationRequestDTO request;

    public InvalidEmailException(String message, RegistrationRequestDTO request) {
        super(message.isEmpty() ? INVALID_EMAIL : message);
        this.request = request;
    }

    public InvalidEmailException() {
        super(INVALID_EMAIL);
    }
}
