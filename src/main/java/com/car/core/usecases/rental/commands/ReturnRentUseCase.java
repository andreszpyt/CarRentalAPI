package com.car.core.usecases.rental.commands;

import com.car.core.entities.Rental;

public interface ReturnRentUseCase {
    Rental execute(Long rentalId);
}
