package com.car.infra.dtos.response;

import com.car.core.entities.enums.CarClass;
import com.car.core.entities.enums.Category;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) for car rental response data.
 *
 * This record contains the complete car information returned from the API,
 * including identification, specifications, pricing, and availability status.
 *
 * @since 1.0
 * @author Car Rental API
 */
@Schema(
        name = "CarResponse",
        title = "Car Response",
        description = "Response payload containing detailed car information from the rental system"
)
public record CarResponse (
        @Schema(
                description = "The unique identifier of the car in the system",
                example = "1"
        )
        Long carId,

        @Schema(
                description = "The brand/manufacturer of the vehicle",
                example = "Toyota"
        )
        String brand,

        @Schema(
                description = "The model name of the vehicle",
                example = "Corolla"
        )
        String model,

        @Schema(
                description = "The category/type of the vehicle (SEDAN, SUV, HATCHBACK, TRUCK)",
                example = "SEDAN"
        )
        Category category,

        @Schema(
                description = "The rental class of the vehicle that determines pricing tier (ECONOMY, COMFORT, PREMIUM, LUXURY)",
                example = "ECONOMY"
        )
        CarClass carClass,

        @Schema(
                description = "The vehicle license plate number (unique identifier for registration)",
                example = "ABC-1234"
        )
        String licensePlate,

        @Schema(
                description = "The manufacturing year of the vehicle",
                example = "2024"
        )
        Integer year,

        @Schema(
                description = "The exterior color of the vehicle",
                example = "Silver"
        )
        String color,

        @Schema(
                description = "The daily rental rate in the system's default currency",
                example = "89.90"
        )
        BigDecimal dailyRate,

        @Schema(
                description = "Indicates whether the vehicle is currently available for rent",
                example = "true"
        )
        boolean available
) {}
