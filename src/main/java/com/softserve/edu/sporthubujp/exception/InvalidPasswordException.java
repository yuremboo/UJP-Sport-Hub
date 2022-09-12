package com.softserve.edu.sporthubujp.exception;

import com.softserve.edu.sporthubujp.dto.RegistrationRequestDTO;
import com.softserve.edu.sporthubujp.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.InvalidObjectException;

@Getter
@Setter
public class InvalidPasswordException extends InvalidObjectException {
    private static final String INVALID_PASSWORD = "password not valid";
    private RegistrationRequestDTO requestDTO;

    public InvalidPasswordException(String message, RegistrationRequestDTO requestDTO) {
        super(message.isEmpty() ? INVALID_PASSWORD : message);
        this.requestDTO = requestDTO;
    }

    public InvalidPasswordException() {
        super(INVALID_PASSWORD);
    }
}
