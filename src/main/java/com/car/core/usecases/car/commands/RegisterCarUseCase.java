package com.car.core.usecases.car.commands;

import com.car.core.entities.Car;

public interface RegisterCarUseCase {
    Car execute(Car car);
}
