package com.car.core.usecases.car.commands;

import com.car.core.gateway.CarGateway;
import com.car.core.usecases.car.exceptions.CarNotFoundException;

public class DeleteCarUseCaseImpl implements DeleteCarUseCase{
    CarGateway carGateway;

    public DeleteCarUseCaseImpl(CarGateway carGateway) {
        this.carGateway = carGateway;
    }

    @Override
    public void execute(Long id) {
        if (carGateway.findById(id).isEmpty()) {
            throw new CarNotFoundException(id);
        }
        carGateway.deleteCar(id);
    }
}
