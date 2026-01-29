package com.car.core.usecases.car.queries;

import com.car.core.entities.Car;
import com.car.core.gateway.CarGateway;

import java.util.Optional;

public class FindByPlateUseCaseImpl implements FindByPlateUseCase {

    private final CarGateway carGateway;

    public FindByPlateUseCaseImpl(CarGateway carGateway) {
        this.carGateway = carGateway;
    }

    @Override
    public Optional<Car> execute(String plate) {
        return carGateway.findCarByPlate(plate);
    }
}
