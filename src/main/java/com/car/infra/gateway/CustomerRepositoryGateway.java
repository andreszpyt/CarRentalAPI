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
        return repository.findByEmail(email)
                .map(mapper::toCustomer);
    }

    @Override
    public Optional<Customer> findByCpf(String cpf) {
        return repository.findByCpf(cpf).map(mapper::toCustomer);
    }

    @Override
    public Customer registerCostumer(Customer costumer) {
        repository.save(mapper.toEntity(costumer));
        return costumer;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return  repository.findById(id).map(mapper::toCustomer);
    }
}
