package com.car.core.gateway;

import com.car.core.entities.Car;

import java.util.List;

public interface CarGateway {

    Car registerCar(Car car);
    List<Car> findCars();
}
