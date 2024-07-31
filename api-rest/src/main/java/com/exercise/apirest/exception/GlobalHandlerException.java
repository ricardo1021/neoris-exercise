package com.exercise.apirest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class GlobalExceptionHandler {



    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public GlobalExceptionHandler() {
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> handleBusinessException(ResourceNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(ex.getErrorCode()).body(ex.getMessage());
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<String> handleInsufficientBalanceException(InsufficientBalanceException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(ex.getErrorCode()).body(ex.getMessage());
    }

}

