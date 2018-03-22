package com.javarightnow.reservation.exception.handler;

import com.javarightnow.reservation.exception.domain.ResponseError;
import org.springframework.http.ResponseEntity;

interface IExceptionHandler {

    default ResponseEntity<Object> buildResponseEntity(Exception ex, ResponseError responseError) {
        if (ex.getCause() != null) {
            responseError.setDebugMessage(ex.getCause().getLocalizedMessage());
        }
        return new ResponseEntity<>(responseError, responseError.getStatus());
    }
} 