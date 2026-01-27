package com.car.infra.presentation;

import com.car.core.entities.Car;
import com.car.core.usecases.car.FindCarsUseCase;
import com.car.core.usecases.car.RegisterCarUseCase;
import com.car.infra.dtos.request.CarRequest;
import com.car.infra.dtos.response.CarResponse;
import com.car.infra.mapper.CarMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    private final CarMapper carMapper;
    private final RegisterCarUseCase registerCarUseCase;
    private final FindCarsUseCase  findCarsUseCase;

    public CarController(CarMapper carMapper, RegisterCarUseCase registerCarUseCase, FindCarsUseCase findCarsUseCase) {
        this.carMapper = carMapper;
        this.registerCarUseCase = registerCarUseCase;
        this.findCarsUseCase = findCarsUseCase;
    }

    @PostMapping
    public ResponseEntity<CarResponse> createCar(@RequestBody CarRequest carRequest) {
        Car car = carMapper.toDomain(carRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(carMapper.toResponse(registerCarUseCase.execute(car)));
    }

    @GetMapping
    public ResponseEntity<List<CarResponse>> findAllCars() {
        List<Car> cars = findCarsUseCase.execute();
        return ResponseEntity.status(HttpStatus.FOUND).body(cars.stream()
                .map(carMapper::toResponse)
                .toList());
    }
}
