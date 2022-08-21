package com.softserve.edu.sporthubujp.exception;

import com.softserve.edu.sporthubujp.entity.ConfirmationToken;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenExpiredException extends IllegalStateException {
    private static final String TOKEN_EXPIRED = "token expired";
    private ConfirmationToken confirmationToken;

    public TokenExpiredException(String message, ConfirmationToken confirmationToken) {
        super(message.isEmpty() ? TOKEN_EXPIRED : message);
        this.confirmationToken = confirmationToken;
    }

    public TokenExpiredException() {
        super(TOKEN_EXPIRED);
    }


}

