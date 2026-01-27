package com.car.core.usecases.car;

import com.car.core.entities.Car;

import java.util.Optional;

public interface FindByPlateUseCase {
    Optional<Car> execute(String plate);
}