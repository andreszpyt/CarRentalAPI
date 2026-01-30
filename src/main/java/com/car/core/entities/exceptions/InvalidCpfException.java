package com.car.core.entities.exceptions;

public class InvalidCpfException extends RuntimeException {
    public InvalidCpfException(String message) {
        super("Invalid Cpf: " + message);
    }
}
