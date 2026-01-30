package com.car.core.usecases.costumer.queries;

import com.car.core.entities.Costumer;
import com.car.core.gateway.CostumerGateway;
import com.car.core.usecases.exception.NotFoundException;

public class FindByEmailUseCaseImpl implements FindByEmailUseCase {
    private final CostumerGateway costumerGateway;

    public FindByEmailUseCaseImpl(CostumerGateway costumerGateway) {
        this.costumerGateway = costumerGateway;
    }

    @Override
    public Costumer execute(String email) {
        return costumerGateway.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Not Found costumer with email " + email));
    }
}
