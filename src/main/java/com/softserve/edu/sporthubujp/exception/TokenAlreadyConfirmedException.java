package com.softserve.edu.sporthubujp.exception;

public class TokenAlreadyConfirmedException extends IllegalStateException{
    private static final String TOKEN_ALREADY_CONFIRMED = "token already confirmed";

    public TokenAlreadyConfirmedException(String message) {
        super(message.isEmpty() ? TOKEN_ALREADY_CONFIRMED : message);
    }

    public TokenAlreadyConfirmedException() {
        super(TOKEN_ALREADY_CONFIRMED);
    }
}
