package com.car.infra.gateway;

import com.car.core.entities.Car;
import com.car.core.gateway.CarGateway;
import com.car.infra.mapper.CarEntityMapper;
import com.car.infra.persistence.CarEntity;
import com.car.infra.persistence.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CarRepositoryGateway implements CarGateway {

    private final CarRepository carRepository;
    private final CarEntityMapper mapper;

    @Override
    public Car registerCar(Car car) {
        CarEntity entity = carRepository.save(mapper.toDomain(car));
        return mapper.toResponse(entity);
    }

    @Override
    public List<Car> findCars() {
        List<CarEntity> cars = carRepository.findAll();
        return cars.stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public Optional<Car> findCarByPlate(String plate) {
        return Optional.of(mapper.toResponse(carRepository.findByLicensePlate(plate)));
    }
}
