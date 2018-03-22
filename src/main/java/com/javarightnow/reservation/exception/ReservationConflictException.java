package com.javarightnow.reservation.exception;

import lombok.Builder;

/**
 * If you face a time conflict in making a reservation, you can throw this exception.
 */
public class ReservationConflictException extends BusinessException {

    @Builder
    private ReservationConflictException(String message, Long code) {
        super(message, code);
    }

} 