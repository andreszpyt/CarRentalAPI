package com.car.core.usecases.costumer.commands;

import com.car.core.entities.Costumer;

public interface RegisterCostumerUseCase {
    Costumer execute(Costumer costumer);
}
