package com.car.core.usecases.car.queries;

import com.car.core.entities.Car;

import java.util.List;

public interface FindCarsByCategoryUseCase {
    List<Car> execute(String category);
}
