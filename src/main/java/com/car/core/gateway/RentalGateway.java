package com.car.core.gateway;

import com.car.core.entities.Rental;

import java.time.LocalDateTime;

public interface RentalGateway {

    public Rental createRental(Rental rental);
    public boolean hasConflictingRental(Long carId, LocalDateTime start, LocalDateTime end);
}
