package com.car.core.usecases.rental.queries;

import com.car.core.entities.Rental;
import com.car.core.gateway.CustomerGateway;
import com.car.core.gateway.RentalGateway;
import com.car.core.usecases.exception.NotFoundException;

import java.util.List;

public class FindRentByCustomerUseCaseImpl implements  FindRentByCustomerUseCase {
    private final RentalGateway rentalGateway;
    private final CustomerGateway customerGateway;

    public FindRentByCustomerUseCaseImpl(RentalGateway rentalGateway, CustomerGateway customerGateway) {
        this.rentalGateway = rentalGateway;
        this.customerGateway = customerGateway;
    }

    @Override
    public List<Rental> execute(Long customerId) {
        customerGateway.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        return rentalGateway.findRentalByCustomer(customerId);
    }
}
