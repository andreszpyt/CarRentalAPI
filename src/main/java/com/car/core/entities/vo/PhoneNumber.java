package com.car.core.entities.vo;

import com.car.core.entities.exceptions.InvalidPhoneNumberException;

public record PhoneNumber(String value) {
    public PhoneNumber {
        if (value == null || !value.matches("^\\+?[1-9]\\d{1,14}$")) {
            throw new InvalidPhoneNumberException("Invalid phone number format");
        }
    }
}