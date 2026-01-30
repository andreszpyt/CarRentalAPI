package com.car.core.usecases.car.queries;

import com.car.core.entities.Car;
import com.car.core.gateway.CarGateway;
import com.car.core.usecases.exception.NotFoundException;

public class FindByIdUseCaseImpl implements  FindByIdUseCase {
    CarGateway carGateway;

    public FindByIdUseCaseImpl(CarGateway carGateway) {
        this.carGateway = carGateway;
    }

    @Override
    public Car execute(Long id) {
        return carGateway.findById(id)
                .orElseThrow(() -> new NotFoundException("Car not found, with id: " + id));
    }
}
