package com.appointment.users.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ApiExceptionResponse {

    private LocalDateTime timestamp;
    private String message;
    private int status;
}
