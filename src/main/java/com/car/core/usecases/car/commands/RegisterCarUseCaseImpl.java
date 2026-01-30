package com.car.core.usecases.car.commands;

import com.car.core.entities.Car;
import com.car.core.gateway.CarGateway;
import com.car.core.usecases.exception.ConflictException;

public class RegisterCarUseCaseImpl implements RegisterCarUseCase {

    private final CarGateway carGateway;

    public RegisterCarUseCaseImpl(CarGateway carGateway) {
        this.carGateway = carGateway;
    }

    @Override
    public Car execute(Car car) {
        if(carGateway.findCarByPlate(car.licensePlate()).isPresent()) {
            throw new ConflictException("Plate alredy exists: " + car.licensePlate());
        }
        return carGateway.registerCar(car);
    }
}
