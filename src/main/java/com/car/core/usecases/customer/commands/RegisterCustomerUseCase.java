package com.car.core.usecases.customer.commands;

import com.car.core.entities.Customer;

public interface RegisterCustomerUseCase {
    Customer execute(Customer costumer);
}
