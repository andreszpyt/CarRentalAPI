package com.car.core.entities.exceptions;

public class InvalidEmailAdressException extends RuntimeException {
    public InvalidEmailAdressException(String emailAdress) {
        super("Invalid Email Address: " + emailAdress);
    }
}
