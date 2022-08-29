package com.softserve.edu.sporthubujp.exception;

import com.softserve.edu.sporthubujp.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailAlreadyTakenException extends IllegalStateException {
    private static final String EMAIL_ALREADY_TAKEN = "email already taken";
    private UserDTO userDTO;

    public EmailAlreadyTakenException(String message, UserDTO userDTO) {
        super(message.isEmpty() ? EMAIL_ALREADY_TAKEN : message);
        this.userDTO = userDTO;
    }

    public EmailAlreadyTakenException() {
        super(EMAIL_ALREADY_TAKEN);
    }

}
