package com.car.infra.gateway;

import com.car.core.entities.Customer;
import com.car.core.gateway.CustomerGateway;
import com.car.infra.mapper.CustomerEntityMapper;
import com.car.infra.persistence.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerRepositoryGateway implements CustomerGateway {
    private final CustomerRepository repository;
    private final CustomerEntityMapper mapper;

    public CustomerRepositoryGateway(CustomerRepository repository, CustomerEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return Optional.ofNullable(mapper.toCostumer(repository.findByEmail(email)));
    }

    @Override
    public Optional<Customer> findByCpf(String cpf) {
        return Optional.ofNullable(mapper.toCostumer(repository.findByCpf(cpf)));
    }

    @Override
    public Customer registerCostumer(Customer costumer) {
        repository.save(mapper.toEntity(costumer));
        return costumer;
    }
}
