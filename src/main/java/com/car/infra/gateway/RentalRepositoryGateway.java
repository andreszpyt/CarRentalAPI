package com.car.infra.gateway;

import com.car.core.entities.Rental;
import com.car.core.gateway.RentalGateway;
import com.car.infra.persistence.RentalRepository;

public class RentalRepositoryGateway implements RentalGateway {
    private final RentalRepository repository;
    private final Rental

    public RentalRepositoryGateway(RentalRepository rentalRepository) {
        this.repository = rentalRepository;
    }

    @Override
    public Rental createRental(Rental rental) {
        return
    }
}
