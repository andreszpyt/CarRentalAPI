package com.car.core.usecases.rental.queries;

import com.car.core.entities.Rental;

public interface FindRentByIdUseCase {
    Rental execute(Long rentalId);
}
