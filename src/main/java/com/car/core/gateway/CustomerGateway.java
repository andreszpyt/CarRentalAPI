package com.car.core.gateway;

import com.car.core.entities.Customer;

import java.util.Optional;

public interface CustomerGateway {

    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByCpf(String cpf);
    Customer registerCostumer(Customer costumer);
    Optional<Customer> findById(Long id);
}
