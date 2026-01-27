package com.car.core.usecases.car;

import com.car.core.entities.Car;
import com.car.core.gateway.CarGateway;

public class RegisterCarUseCaseImpl implements RegisterCarUseCase {

    private final CarGateway carGateway;

    public RegisterCarUseCaseImpl(CarGateway carGateway) {
        this.carGateway = carGateway;
    }

    @Override
    public Car execute(Car car) {
        return carGateway.registerCar(car);
    }
}
