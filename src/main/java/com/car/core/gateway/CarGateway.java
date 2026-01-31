package com.car.core.gateway;

import com.car.core.entities.Car;

import java.util.List;
import java.util.Optional;

public interface CarGateway {

    Car registerCar(Car car);
    List<Car> findCars();
    Optional<Car> findCarByPlate(String plate);
    Car updateCar(Long id, Car car);
    void deleteCar(Long id);
    Optional<Car> findById(Long id);
    void updateAvailability(Long id, Boolean availability);
}
