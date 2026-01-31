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
        CarEntity entity = carRepository.findByLicensePlate(plate);
        if (entity == null) return Optional.empty();
        return Optional.of(mapper.toResponse(entity));
    }

    @Override
    public Car updateCar(Long id, Car car) {
        CarEntity entity = mapper.toDomain(car);
        entity.setId(id);
        return mapper.toResponse(carRepository.save(entity));
    }

    @Override
    public Optional<Car> findById(Long id) {
        Optional<CarEntity> entity = carRepository.findById(id);
        return entity.map(mapper::toResponse);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public void updateAvailability(Long id, Boolean availability) {
        CarEntity entity = carRepository.findById(id).get();
        entity.setAvailable(availability);
        carRepository.save(entity);
    }
}
