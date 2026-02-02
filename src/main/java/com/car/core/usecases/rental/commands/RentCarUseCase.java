package com.car.core.usecases.rental.commands;

import com.car.core.entities.Rental;

public interface RentCarUseCase {
    public Rental execute(Rental rental);
}
