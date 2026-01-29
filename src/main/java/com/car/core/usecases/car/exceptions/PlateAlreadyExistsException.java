package com.car.core.usecases.car.exceptions;

public class PlateAlreadyExistsException extends RuntimeException {
    public PlateAlreadyExistsException(String Plate) {
        super("The plate " + Plate + " already exists!");
    }
}
