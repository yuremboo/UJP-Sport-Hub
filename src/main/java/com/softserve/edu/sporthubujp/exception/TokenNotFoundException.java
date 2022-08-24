package com.softserve.edu.sporthubujp.exception;

import java.util.NoSuchElementException;

public class TokenNotFoundException extends NoSuchElementException {
    private static final String TOKEN_NOT_FOUND = "token not found";

    public TokenNotFoundException(String message) {
        super(message.isEmpty() ? TOKEN_NOT_FOUND : message);
    }

    public TokenNotFoundException() {
        super(TOKEN_NOT_FOUND);
    }
}
