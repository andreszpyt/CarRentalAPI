package com.car.core.gateway;

import com.car.core.entities.Car;

import java.util.List;
import java.util.Optional;

public interface CarGateway {

    Car registerCar(Car car);
    List<Car> findCars();
    Optional<Car> findCarByPlate(String plate);
}
