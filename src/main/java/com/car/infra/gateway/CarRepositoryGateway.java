package com.car.infra.gateway;

import com.car.core.entities.Car;
import com.car.core.gateway.CarGateway;
import com.car.infra.mapper.CarEntityMapper;
import com.car.infra.persistence.CarEntity;
import com.car.infra.persistence.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
}
