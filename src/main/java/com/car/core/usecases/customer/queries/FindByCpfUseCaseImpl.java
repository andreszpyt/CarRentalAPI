package com.car.core.usecases.customer.queries;

import com.car.core.entities.Customer;
import com.car.core.gateway.CustomerGateway;
import com.car.core.usecases.exception.NotFoundException;

public class FindByCpfUseCaseImpl implements FindByCpfUseCase {
    private final CustomerGateway customerGateway;

    public FindByCpfUseCaseImpl(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    @Override
    public Customer execute(String cpf) {
        return customerGateway.findByCpf(cpf).orElseThrow(() -> new  NotFoundException("Not Found costumer with cpf " + cpf));
    }
}
