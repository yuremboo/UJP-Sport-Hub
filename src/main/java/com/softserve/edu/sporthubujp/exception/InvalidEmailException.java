package com.softserve.edu.sporthubujp.exception;

import java.io.InvalidObjectException;

public class InvalidEmailException extends InvalidObjectException {
    private static final String INVALID_EMAIL = "email not valid";

    public InvalidEmailException(String message) {
        super(message.isEmpty() ? INVALID_EMAIL : message);
    }

    public InvalidEmailException() {
        super(INVALID_EMAIL);
    }
}
