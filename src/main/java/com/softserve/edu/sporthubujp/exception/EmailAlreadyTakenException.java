package com.softserve.edu.sporthubujp.exception;

public class EmailAlreadyTakenException extends IllegalStateException {
    private static final String EMAIL_ALREADY_TAKEN = "email already taken";

    public EmailAlreadyTakenException(String message) {
        super(message.isEmpty() ? EMAIL_ALREADY_TAKEN : message);
    }

    public EmailAlreadyTakenException() {
        super(EMAIL_ALREADY_TAKEN);
    }
}
