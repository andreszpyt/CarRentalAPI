package com.car.infra.dtos.request;

import com.car.core.entities.enums.CarClass;
import com.car.core.entities.enums.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) for car rental creation and update requests.
 *
 * This record contains all the necessary information to create or update a car
 * in the rental system, including vehicle specifications, pricing information,
 * and availability status.
 *
 * @since 1.0
 * @author Car Rental API
 */
@Schema(
        name = "CarRequest",
        title = "Car Request",
        description = "Request payload for creating or updating a car in the rental system",
        example = """
                {
                  "brand": "Toyota",
                  "model": "Corolla",
                  "category": "SEDAN",
                  "carClass": "ECONOMY",
                  "licensePlate": "ABC-1234",
                  "year": 2024,
                  "color": "Silver",
                  "dailyRate": 89.90,
                  "available": true
                }
                """
)
public record CarRequest(
        @NotBlank(message = "Brand cannot be blank")
        @Size(min = 1, max = 50, message = "Brand must be between 1 and 50 characters")
        @Schema(
                description = "The brand/manufacturer of the vehicle (e.g., Toyota, Honda, Ford)",
                example = "Toyota",
                minLength = 1,
                maxLength = 50,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String brand,

        @NotBlank(message = "Model cannot be blank")
        @Size(min = 1, max = 50, message = "Model must be between 1 and 50 characters")
        @Schema(
                description = "The model name of the vehicle (e.g., Corolla, Civic, Focus)",
                example = "Corolla",
                minLength = 1,
                maxLength = 50,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String model,

        @NotNull(message = "Category cannot be null")
        @Schema(
                description = "The category/type of the vehicle (e.g., SEDAN, SUV, HATCHBACK, TRUCK)",
                example = "SEDAN",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Category category,

        @NotNull(message = "Car class cannot be null")
        @Schema(
                description = "The rental class of the vehicle that determines pricing tier (e.g., ECONOMY, COMFORT, PREMIUM, LUXURY)",
                example = "ECONOMY",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        CarClass carClass,

        @NotBlank(message = "License plate cannot be blank")
        @Size(min = 1, max = 20, message = "License plate must be between 1 and 20 characters")
        @Pattern(regexp = "^[A-Z0-9\\-]+$", message = "License plate must contain only uppercase letters, numbers, and hyphens")
        @Schema(
                description = "The vehicle license plate number (unique identifier for registration)",
                example = "ABC-1234",
                pattern = "^[A-Z0-9\\-]{1,20}$",
                minLength = 1,
                maxLength = 20,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String licensePlate,

        @NotNull(message = "Year cannot be null")
        @Min(value = 1990, message = "Year must be greater than or equal to 1990")
        @Max(value = 2100, message = "Year must be less than or equal to 2100")
        @Schema(
                description = "The manufacturing year of the vehicle",
                example = "2024",
                minimum = "1990",
                maximum = "2100",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Integer year,

        @NotBlank(message = "Color cannot be blank")
        @Size(min = 1, max = 50, message = "Color must be between 1 and 50 characters")
        @Schema(
                description = "The exterior color of the vehicle",
                example = "Silver",
                minLength = 1,
                maxLength = 50,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String color,

        @NotNull(message = "Daily rate cannot be null")
        @DecimalMin(value = "0.0", inclusive = false, message = "Daily rate must be greater than 0")
        @Digits(integer = 10, fraction = 2, message = "Daily rate must have at most 10 integer digits and 2 decimal places")
        @Schema(
                description = "The daily rental rate in the system's default currency (typically USD or local currency)",
                example = "89.90",
                minimum = "0",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        BigDecimal dailyRate,

        @Schema(
                description = "Indicates whether the vehicle is currently available for rent (true) or not (false)",
                example = "true",
                defaultValue = "true",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        boolean available
){}
