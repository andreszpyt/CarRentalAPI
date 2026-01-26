package com.car.infra.mapper;

import com.car.core.entities.Car;
import com.car.infra.dtos.request.CarRequest;
import com.car.infra.dtos.response.CarResponse;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    public Car toDomain(CarRequest carRequest) {
        return new Car(
                null,
                carRequest.brand(),
                carRequest.model(),
                carRequest.category(),
                carRequest.licensePlate(),
                carRequest.year(),
                carRequest.color(),
                carRequest.dailyRate(),
                carRequest.available()
        );
    }


    public CarResponse toResponse(Car car) {
        return new CarResponse(
                car.id(),
                car.brand(),
                car.model(),
                car.category(),
                car.licensePlate(),
                car.year(),
                car.color(),
                car.dailyRate(),
                car.available()
        );
    }
}
