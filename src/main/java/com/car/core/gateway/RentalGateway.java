package com.car.core.gateway;

import com.car.core.entities.Rental;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RentalGateway {

    Rental createRental(Rental rental);
    boolean hasConflictingRental(Long carId, LocalDateTime start, LocalDateTime end);
    Optional<Rental> findRentalById(Long rentalId);
    List<Rental> findRentalByCustomer(Long customerId);
}
