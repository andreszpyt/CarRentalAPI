package com.car.core.usecases.car.commands;

import com.car.core.entities.Car;

public interface UpdateCarUseCase {
    Car execute(Long id, Car car);
}
