package com.car.core.usecases.car;

import com.car.core.entities.Car;
import com.car.core.gateway.CarGateway;

public class UpdateCarUseCaseImpl implements UpdateCarUseCase {
    private final CarGateway carGateway;

    public UpdateCarUseCaseImpl(CarGateway carGateway) {
        this.carGateway = carGateway;
    }

    @Override
    public Car execute(Long id, Car car) {
        return carGateway.updateCar(id, car);
    }
}
