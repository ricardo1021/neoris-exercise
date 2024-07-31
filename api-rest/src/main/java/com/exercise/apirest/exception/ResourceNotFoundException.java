package com.exercise.apirest.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

    private String message;
    private Integer errorCode;

    public ResourceNotFoundException(String message, Integer errorCode) {
        super();
        this.message = message;
        this.errorCode = errorCode;
    }
}
