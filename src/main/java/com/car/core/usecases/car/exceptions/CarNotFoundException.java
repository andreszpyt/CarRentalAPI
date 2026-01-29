package com.car.core.usecases.car.exceptions;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(Long id) {
        super("could not find car with id " + id);
    }
}
