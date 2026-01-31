package com.car.core.usecases.customer.queries;

import com.car.core.entities.Customer;

public interface FindByEmailUseCase {
    Customer execute(String email);
}
