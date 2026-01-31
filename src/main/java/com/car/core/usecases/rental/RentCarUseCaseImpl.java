package com.car.core.usecases.rental;

import com.car.core.entities.Rental;
import com.car.core.gateway.RentalGateway;

public class RentCarUseCaseImpl implements RentCarUseCase{
    private final RentalGateway rentalGateway;

    public RentCarUseCaseImpl(RentalGateway rentalGateway) {
        this.rentalGateway = rentalGateway;
    }

    @Override
    public Rental execute(Rental rental) {
        return null;
    }
}
