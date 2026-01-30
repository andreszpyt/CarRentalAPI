package com.car.infra.gateway;

import com.car.core.entities.Costumer;
import com.car.core.gateway.CostumerGateway;
import com.car.infra.mapper.CostumerEntityMapper;
import com.car.infra.persistence.CostumerRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CostumerRepositoryGateway implements CostumerGateway {
    private final CostumerRepository repository;
    private final CostumerEntityMapper mapper;

    public CostumerRepositoryGateway(CostumerRepository repository, CostumerEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Costumer> findByEmail(String email) {
        return Optional.ofNullable(mapper.toCostumer(repository.findByEmail(email)));
    }

    @Override
    public Optional<Costumer> findByCpf(String cpf) {
        return Optional.ofNullable(mapper.toCostumer(repository.findByCpf(cpf)));
    }

    @Override
    public Costumer registerCostumer(Costumer costumer) {
        repository.save(mapper.toEntity(costumer));
        return costumer;
    }
}
