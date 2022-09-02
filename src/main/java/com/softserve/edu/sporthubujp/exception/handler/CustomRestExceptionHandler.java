package com.softserve.edu.sporthubujp.exception.handler;

import javax.mail.SendFailedException;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import com.softserve.edu.sporthubujp.dto.RegistrationRequestDTO;
import com.softserve.edu.sporthubujp.dto.UserDTO;
import com.softserve.edu.sporthubujp.entity.ConfirmationToken;
import com.softserve.edu.sporthubujp.entity.User;
import com.softserve.edu.sporthubujp.exception.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(InvalidEmailException.class)
    protected ResponseEntity<Object> handleInvalidEmail(
            InvalidEmailException ex) {

        ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage(ex.toString());

        RegistrationRequestDTO request = ex.getRequest();
        ApiValidationError apiValidationError = new ApiValidationError(List.of(request), ex.getMessage());
        apiValidationError.setField("email");
        apiValidationError.setRejectedValue(request.getEmail());

        apiError.setSubErrors(List.of(apiValidationError));

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(TokenNotFoundException.class)
    protected ResponseEntity<Object> handleTokenNotFound(
            TokenNotFoundException ex) {

        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage(ex.toString());

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(TokenAlreadyConfirmedException.class)
    protected ResponseEntity<Object> handleTokenAlreadyConfirmed(
            TokenAlreadyConfirmedException ex) {

        ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage(ex.toString());

        ConfirmationToken token = ex.getConfirmationToken();
        ApiValidationError apiValidationError = new ApiValidationError(List.of(token), ex.getMessage());
        apiValidationError.setField("confirmedAt");
        apiValidationError.setRejectedValue(token.getConfirmedAt());

        apiError.setSubErrors(List.of(apiValidationError));

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(TokenExpiredException.class)
    protected ResponseEntity<Object> handleTokenExpired(
            TokenExpiredException ex) {

        ApiError apiError = new ApiError(HttpStatus.GONE);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage(ex.toString());

        ConfirmationToken token = ex.getConfirmationToken();
        ApiValidationError apiValidationError = new ApiValidationError(List.of(token), ex.getMessage());
        apiValidationError.setField("expiresAt");
        apiValidationError.setRejectedValue(token.getExpiresAt());

        apiError.setSubErrors(List.of(apiValidationError));

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EmailAlreadyTakenException.class)
    protected ResponseEntity<Object> handleEmailAlreadyTaken(
            EmailAlreadyTakenException ex) {

        ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage(ex.toString());

        UserDTO userDTO = ex.getUserDTO();
        ApiValidationError apiValidationError = new ApiValidationError(List.of(userDTO), ex.getMessage());
        apiValidationError.setField("email");
        apiValidationError.setRejectedValue(userDTO.getEmail());

        apiError.setSubErrors(List.of(apiValidationError));

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<Object> handleCustomUsernameNotFound(
            BadCredentialsException ex) {

        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage(ex.toString());

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(SendFailedException.class)
    protected ResponseEntity<Object> handleSendFailed(
            SendFailedException ex) {

        ApiError apiError = new ApiError(HttpStatus.BAD_GATEWAY);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage(ex.toString());

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EmailNotConfirmedException.class)
    protected ResponseEntity<Object> handleEmailNotConfirmed(
            EmailNotConfirmedException ex) {

        ApiError apiError = new ApiError(HttpStatus.FORBIDDEN);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage(ex.toString());

        User user = ex.getUser();
        ApiValidationError apiValidationError = new ApiValidationError(List.of(user), ex.getMessage());
        apiValidationError.setField("email");
        apiValidationError.setRejectedValue(user.getEmail());

        apiError.setSubErrors(List.of(apiValidationError));

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    protected ResponseEntity<Object> handleInternalAuthenticationService(
            InternalAuthenticationServiceException ex) {

        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage(ex.toString());

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex) {

        ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage(ex.getConstraintViolations().toString());

        return buildResponseEntity(apiError);
    }
}
