package com.softserve.edu.sporthubujp.exception;

import com.softserve.edu.sporthubujp.entity.ConfirmationToken;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenAlreadyConfirmedException extends IllegalStateException {
    private static final String TOKEN_ALREADY_CONFIRMED = "token already confirmed";
    private ConfirmationToken confirmationToken;

    public TokenAlreadyConfirmedException(String message, ConfirmationToken confirmationToken) {
        super(message.isEmpty() ? TOKEN_ALREADY_CONFIRMED : message);
        this.confirmationToken = confirmationToken;
    }


    public ConfirmationToken getConfirmationToken() {
        return confirmationToken;
    }
}
