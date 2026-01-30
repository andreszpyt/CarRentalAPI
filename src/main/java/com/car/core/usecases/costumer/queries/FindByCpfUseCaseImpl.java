package com.car.core.usecases.costumer.queries;

import com.car.core.entities.Costumer;
import com.car.core.gateway.CostumerGateway;
import com.car.core.usecases.exception.NotFoundException;

public class FindByCpfUseCaseImpl implements FindByCpfUseCase {
    private final CostumerGateway costumerGateway;

    public FindByCpfUseCaseImpl(CostumerGateway costumerGateway) {
        this.costumerGateway = costumerGateway;
    }

    @Override
    public Costumer execute(String cpf) {
        return costumerGateway.findByCpf(cpf).orElseThrow(() -> new  NotFoundException("Not Found costumer with cpf " + cpf));
    }
}
