package com.car.core.entities.vo;

import com.car.core.entities.exceptions.InvalidEmailAdressException;

public record Email(String address) {
    public Email {
        if (address == null || !address.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new InvalidEmailAdressException(address);
        }
    }
}