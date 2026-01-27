package com.car.core.usecases.car;

import com.car.core.entities.Car;

import java.util.List;

public interface FindCarsUseCase {
    List<Car> execute();
}
