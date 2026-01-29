package com.car.core.usecases.car;

import com.car.core.entities.Car;
import com.car.core.gateway.CarGateway;
import com.car.core.usecases.car.exceptions.PlateAlreadyExistsException;

public class RegisterCarUseCaseImpl implements RegisterCarUseCase {

    private final CarGateway carGateway;

    public RegisterCarUseCaseImpl(CarGateway carGateway) {
        this.carGateway = carGateway;
    }

    @Override
    public Car execute(Car car) {
        if(carGateway.findCarByPlate(car.licensePlate()).isPresent()) {
            throw new PlateAlreadyExistsException(car.licensePlate());
        }
        return carGateway.registerCar(car);
    }
}
