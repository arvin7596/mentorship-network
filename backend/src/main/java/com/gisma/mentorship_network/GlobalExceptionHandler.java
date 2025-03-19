package com.gisma.mentorship_network;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {
    public record ErrorResponse(
        String message,
        String status
    ) {}

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex) {
        ErrorResponse error = new ErrorResponse(
            ex.getReason(),
            ex.getStatusCode().toString()
        );
        return new ResponseEntity<>(error, ex.getStatusCode());
    }
    
}