package com.car.core.usecases.customer.queries;

import com.car.core.entities.Customer;
import com.car.core.gateway.CustomerGateway;
import com.car.core.usecases.exception.NotFoundException;

public class FindByEmailUseCaseImpl implements FindByEmailUseCase {
    private final CustomerGateway customerGateway;

    public FindByEmailUseCaseImpl(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    @Override
    public Customer execute(String email) {
        return customerGateway.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Not Found costumer with email " + email));
    }
}
