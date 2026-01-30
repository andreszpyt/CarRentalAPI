package com.car.core.gateway;

import com.car.core.entities.Costumer;

import java.util.Optional;

public interface CostumerGateway {

    Optional<Costumer> findByEmail(String email);
    Optional<Costumer> findByCpf(String cpf);
}
