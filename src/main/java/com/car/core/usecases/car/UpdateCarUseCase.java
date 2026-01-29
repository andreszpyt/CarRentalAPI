package com.car.core.usecases.car;

import com.car.core.entities.Car;

public interface UpdateCarUseCase {
    Car execute(Long id, Car car);
}
