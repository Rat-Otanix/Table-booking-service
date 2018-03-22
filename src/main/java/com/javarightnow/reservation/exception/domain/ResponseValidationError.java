package com.javarightnow.reservation.exception.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ResponseValidationError extends ResponseSubError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    ResponseValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
} 