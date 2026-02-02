package com.car.core.usecases.rental.queries;

import com.car.core.entities.Rental;
import com.car.core.gateway.RentalGateway;
import com.car.core.usecases.exception.NotFoundException;

public class FindRentByIdUseCaseImpl implements FindRentByIdUseCase {
    private final RentalGateway rentalGateway;

    public FindRentByIdUseCaseImpl(RentalGateway rentalGateway) {
        this.rentalGateway = rentalGateway;
    }

    @Override
    public Rental execute(Long rentalId) {
        return rentalGateway.findRentalById(rentalId).orElseThrow(() -> new NotFoundException("Rental not found."));
    }
}
