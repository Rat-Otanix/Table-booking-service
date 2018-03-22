package com.javarightnow.reservation.exception.enums;

/**
 * Each model points an Entity. We usually use it in exception messages.
 *
 * @author hadi
 */
public enum Model {

    TABLE("table"),
    CUSTOMER("customer"),
    RESERVATION("reservation");

    public final String value;

    Model(final String value) {
        this.value = value;
    }

    public String toValue() {
        return this.value;
    }
}
