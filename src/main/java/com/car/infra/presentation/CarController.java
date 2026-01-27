package com.car.infra.presentation;

import com.car.core.entities.Car;
import com.car.core.usecases.car.RegisterCarUseCase;
import com.car.infra.dtos.request.CarRequest;
import com.car.infra.dtos.response.CarResponse;
import com.car.infra.mapper.CarMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
public class CarController {

    private final CarMapper carMapper;
    private final RegisterCarUseCase registerCarUseCase;

    public CarController(CarMapper carMapper, RegisterCarUseCase registerCarUseCase) {
        this.carMapper = carMapper;
        this.registerCarUseCase = registerCarUseCase;
    }

    @PostMapping
    public ResponseEntity<CarResponse> createCar(@RequestBody CarRequest carRequest) {
        Car car = carMapper.toDomain(carRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(carMapper.toResponse(registerCarUseCase.execute(car)));
    }


}
