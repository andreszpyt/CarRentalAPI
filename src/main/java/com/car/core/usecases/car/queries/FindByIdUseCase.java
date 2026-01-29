package com.car.core.usecases.car.queries;

import com.car.core.entities.Car;

public interface FindByIdUseCase {
    Car execute(Long id);
}
