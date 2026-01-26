package com.car.infra.mapper;

import com.car.core.entities.Car;
import com.car.infra.persistence.CarEntity;
import org.springframework.stereotype.Component;

@Component
public class CarEntityMapper {

    public CarEntity toDomain(Car car) {
        return new CarEntity(
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

    public Car toResponse(CarEntity car) {
        return new Car(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getCategory(),
                car.getLicensePlate(),
                car.getYear(),
                car.getColor(),
                car.getDailyRate(),
                car.isAvailable()
        );
    }
}
