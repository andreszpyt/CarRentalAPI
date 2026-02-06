package com.car.core.usecases.customer.commands;

import com.car.core.entities.Customer;
import com.car.core.gateway.CustomerGateway;
import com.car.core.security.PasswordEncryptor;
import com.car.core.usecases.exception.ConflictException;

public class RegisterCustomerUseCaseImpl implements RegisterCustomerUseCase {
    private final CustomerGateway customerGateway;
    private final PasswordEncryptor passwordEncryptor;

    public RegisterCustomerUseCaseImpl(CustomerGateway customerGateway, PasswordEncryptor passwordEncryptor) {
        this.customerGateway = customerGateway;
        this.passwordEncryptor = passwordEncryptor;
    }

    @Override
    public Customer execute(Customer costumer) {

        customerGateway.findByCpf(costumer.cpf().value())
                .ifPresent(costumerEntity -> {throw new ConflictException("CPF already exists");});

        customerGateway.findByEmail(costumer.email().address())
                .ifPresent(costumerEntity -> {throw new ConflictException("Email already exists");});

        String hashedPassword = passwordEncryptor.encrypt(costumer.password());

        Customer secureCostumer = new Customer(
                null,
                costumer.name(),
                costumer.email(),
                hashedPassword,
                costumer.cpf(),
                costumer.phoneNumber(),
                costumer.driverLicense(),
                costumer.birthDate(),
                "USER"
        );


        return customerGateway.registerCustomer(secureCostumer);
    }
}
