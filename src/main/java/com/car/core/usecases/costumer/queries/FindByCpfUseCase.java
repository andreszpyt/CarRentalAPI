package com.car.core.usecases.costumer.queries;

import com.car.core.entities.Costumer;

public interface FindByCpfUseCase {
    Costumer execute(String cpf);
}
