package com.car.core.usecases.car.queries;

import com.car.core.entities.Car;
import com.car.core.gateway.CarGateway;
import com.car.core.usecases.exception.NotFoundException;

import java.util.List;

public class FindCarsByCategoryUseCaseImpl implements FindCarsByCategoryUseCase{
    private final CarGateway carGateway;

    public FindCarsByCategoryUseCaseImpl(CarGateway carGateway) {
        this.carGateway = carGateway;
    }

    @Override
    public List<Car> execute(String category) {
        List<Car> car = carGateway.findCarByCategory(category);
        if(car.isEmpty()) throw new NotFoundException("Car not found");
        return car;
    }
}
