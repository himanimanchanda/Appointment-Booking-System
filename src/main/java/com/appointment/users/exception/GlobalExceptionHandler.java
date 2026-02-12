package com.appointment.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404 Handler
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiExceptionResponse> handleNotFound(ResourceNotFoundException ex){

        ApiExceptionResponse error = new ApiExceptionResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    // 409 Handler (Duplicate User)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiExceptionResponse> handleUserExists(UserAlreadyExistsException ex){

        ApiExceptionResponse error = new ApiExceptionResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                HttpStatus.CONFLICT.value()
        );

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }


    // Generic Handler (VERY IMPORTANT)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionResponse> handleGeneric(Exception ex){

        ApiExceptionResponse error = new ApiExceptionResponse(
                LocalDateTime.now(),
                "Internal Server Error",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiExceptionResponse> handleInvalidCredentials(InvalidCredentialsException ex){

        ApiExceptionResponse error = new ApiExceptionResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                HttpStatus.UNAUTHORIZED.value()
        );

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccountDisabledException.class)
    public ResponseEntity<ApiExceptionResponse> handleAccountDisabled(AccountDisabledException ex){

        ApiExceptionResponse error = new ApiExceptionResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                HttpStatus.FORBIDDEN.value()
        );

        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

}
