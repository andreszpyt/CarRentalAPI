package com.car.core.usecases.rental.queries;

import com.car.core.entities.Rental;

import java.util.List;

public interface FindRentByCustomerUseCase {
    List<Rental> execute(Long customerId);
}
