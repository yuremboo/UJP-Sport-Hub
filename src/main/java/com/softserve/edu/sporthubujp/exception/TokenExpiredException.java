package com.softserve.edu.sporthubujp.exception;

public class TokenExpiredException extends IllegalStateException{
    private static final String TOKEN_EXPIRED = "token expired";

    public TokenExpiredException(String message) {
        super(message.isEmpty() ? TOKEN_EXPIRED : message);
    }

    public TokenExpiredException() {
        super(TOKEN_EXPIRED);
    }
}

