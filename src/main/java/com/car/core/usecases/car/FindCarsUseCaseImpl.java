package com.car.core.usecases.car;

import com.car.core.entities.Car;
import com.car.core.gateway.CarGateway;

import java.util.List;

public class FindCarsUseCaseImpl implements FindCarsUseCase {

    private final CarGateway carGateway;

    public FindCarsUseCaseImpl(CarGateway carGateway) {
        this.carGateway = carGateway;
    }

    @Override
    public List<Car> execute() {
        return carGateway.findCars();
    }
}
