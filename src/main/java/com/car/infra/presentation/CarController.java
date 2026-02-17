package com.car.infra.presentation;

import com.car.core.entities.Car;
import com.car.core.usecases.car.commands.DeleteCarUseCase;
import com.car.core.usecases.car.queries.FindByIdUseCase;
import com.car.core.usecases.car.queries.FindByPlateUseCase;
import com.car.core.usecases.car.queries.FindCarsByCategoryUseCase;
import com.car.core.usecases.car.queries.FindCarsUseCase;
import com.car.core.usecases.car.commands.RegisterCarUseCase;
import com.car.core.usecases.car.commands.UpdateCarUseCase;
import com.car.infra.dtos.request.CarRequest;
import com.car.infra.dtos.response.CarResponse;
import com.car.infra.mapper.CarMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing car inventory in the rental system.
 *
 * This controller handles all car-related operations including creation, retrieval,
 * updating, and deletion of cars, as well as searching cars by various criteria.
 *
 * @since 1.0
 * @author Car Rental API
 */
@RestController
@RequestMapping("/car")
@Tag(
        name = "Car Management",
        description = "Endpoints for managing cars in the rental system. Includes operations for creating, updating, deleting, and retrieving car information."
)
public class CarController {

    private final CarMapper carMapper;
    private final RegisterCarUseCase registerCarUseCase;
    private final FindCarsUseCase  findCarsUseCase;
    private final FindByPlateUseCase findByPlateUseCase;
    private final UpdateCarUseCase updateCarUseCase;
    private final FindByIdUseCase findByIdUseCase;
    private final DeleteCarUseCase deleteCarUseCase;
    private final FindCarsByCategoryUseCase findCarsByCategoryUseCase;

    public CarController(CarMapper carMapper, RegisterCarUseCase registerCarUseCase, FindCarsUseCase findCarsUseCase, FindByPlateUseCase findByPlateUseCase, UpdateCarUseCase updateCarUseCase, FindByIdUseCase findByIdUseCase, DeleteCarUseCase deleteCarUseCase, FindCarsByCategoryUseCase findCarsByCategoryUseCase) {
        this.carMapper = carMapper;
        this.registerCarUseCase = registerCarUseCase;
        this.findCarsUseCase = findCarsUseCase;
        this.findByPlateUseCase = findByPlateUseCase;
        this.updateCarUseCase = updateCarUseCase;
        this.findByIdUseCase = findByIdUseCase;
        this.deleteCarUseCase = deleteCarUseCase;
        this.findCarsByCategoryUseCase = findCarsByCategoryUseCase;
    }


    @PostMapping
    @Operation(
            summary = "Create a new car",
            description = "Creates a new car in the rental system with the provided details. The car will be immediately available for rental."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Car successfully created",
                    content = @Content(schema = @Schema(implementation = CarResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request body - validation failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    public ResponseEntity<CarResponse> createCar(@RequestBody CarRequest carRequest) {
        Car car = carMapper.toDomain(carRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(carMapper.toResponse(registerCarUseCase.execute(car)));
    }

    @GetMapping
    @Operation(
            summary = "Retrieve all cars",
            description = "Retrieves a complete list of all cars available in the rental system, including their availability status."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "302",
                    description = "List of cars successfully retrieved",
                    content = @Content(schema = @Schema(implementation = CarResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    public ResponseEntity<List<CarResponse>> findAllCars() {
        List<Car> cars = findCarsUseCase.execute();
        return ResponseEntity.status(HttpStatus.FOUND).body(cars.stream()
                .map(carMapper::toResponse)
                .toList());
    }

    @GetMapping("/plate/{plate}")
    @Operation(
            summary = "Find car by license plate",
            description = "Searches for a specific car in the system using its unique license plate number."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Car found successfully",
                    content = @Content(schema = @Schema(implementation = CarResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Car with the specified license plate not found"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    public ResponseEntity<CarResponse> findCarByPlate(
            @PathVariable
            @Parameter(description = "The license plate number of the car to retrieve (e.g., ABC-1234)")
            String plate) {
        return findByPlateUseCase.execute(plate)
                .map(car -> ResponseEntity.ok(carMapper.toResponse(car)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Update an existing car",
            description = "Updates the details of an existing car in the rental system. Requires the car ID and the new car information."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Car successfully updated",
                    content = @Content(schema = @Schema(implementation = CarResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request body - validation failed"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Car with the specified ID not found"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    public ResponseEntity<CarResponse> updateCar(
            @PathVariable
            @Parameter(description = "The unique identifier of the car to update")
            Long id,
            @RequestBody CarRequest carRequest) {
        Car response =  updateCarUseCase.execute(id, carMapper.toDomain(carRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(carMapper.toResponse(response));
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Retrieve car by ID",
            description = "Retrieves the detailed information of a specific car using its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Car found successfully",
                    content = @Content(schema = @Schema(implementation = CarResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Car with the specified ID not found"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    public ResponseEntity<CarResponse> findCarById(
            @PathVariable
            @Parameter(description = "The unique identifier of the car to retrieve")
            Long id) {
        Car car = findByIdUseCase.execute(id);
        return ResponseEntity.ok(carMapper.toResponse(car));
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Delete a car",
            description = "Removes a car from the rental system. The car will no longer be available for rental after deletion."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Car successfully deleted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Car with the specified ID not found"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    public ResponseEntity<Void> deleteCar(
            @PathVariable
            @Parameter(description = "The unique identifier of the car to delete")
            Long id) {
        deleteCarUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    @Operation(
            summary = "Find cars by category",
            description = "Searches for all cars in the system that belong to a specific category (e.g., SEDAN, SUV, HATCHBACK, TRUCK)."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "302",
                    description = "List of cars in the specified category successfully retrieved",
                    content = @Content(schema = @Schema(implementation = CarResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid category parameter"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    public ResponseEntity<List<CarResponse>> findCarsByCategory(
            @PathVariable
            @Parameter(description = "The car category to search for (e.g., SEDAN, SUV, HATCHBACK, TRUCK)")
            String category) {
        return ResponseEntity.status(HttpStatus.FOUND).body(findCarsByCategoryUseCase.execute(category).stream()
                .map(carMapper::toResponse)
                .toList());
    }
}
