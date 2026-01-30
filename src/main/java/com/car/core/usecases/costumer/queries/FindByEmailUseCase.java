package com.car.core.usecases.costumer.queries;

import com.car.core.entities.Costumer;

public interface FindByEmailUseCase {
    Costumer execute(String email);
}
