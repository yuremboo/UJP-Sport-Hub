package com.softserve.edu.sporthubujp.exception;

import com.softserve.edu.sporthubujp.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailNotConfirmedException extends IllegalStateException{
    private static final String EMAIL_NOT_CONFIRMED = "email %s not confirmed";
    private User user;

    public EmailNotConfirmedException(String message, User user) {
        super(message.isEmpty() ? EMAIL_NOT_CONFIRMED : message);
        this.user = user;
    }

    public EmailNotConfirmedException() {
        super(EMAIL_NOT_CONFIRMED);
    }
}
