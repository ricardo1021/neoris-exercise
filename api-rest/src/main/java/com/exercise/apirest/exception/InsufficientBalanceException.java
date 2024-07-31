package com.exercise.apirest.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InsufficientBalanceException extends RuntimeException {

    private String message;
    private Integer errorCode;

    public InsufficientBalanceException(String message, Integer errorCode) {
        super();
        this.message = message;
        this.errorCode = errorCode;
    }
}
