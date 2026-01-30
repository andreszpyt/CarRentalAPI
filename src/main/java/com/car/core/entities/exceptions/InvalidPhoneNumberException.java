package com.car.core.entities.exceptions;

public class InvalidPhoneNumberException extends RuntimeException {
    public InvalidPhoneNumberException(String message) {
        super("Invalid phone number format");
    }
}
