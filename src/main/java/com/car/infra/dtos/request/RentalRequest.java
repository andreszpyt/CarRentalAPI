package com.car.infra.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for rental creation and update requests.
 *
 * This record contains all the necessary information to create or update a car rental,
 * including the car selection and rental period details (pickup and expected return dates).
 *
 * @since 1.0
 * @author Car Rental API
 */
@Schema(
        name = "RentalRequest",
        title = "Rental Request",
        description = "Request payload for creating or updating a car rental reservation",
        example = """
                {
                  "carId": 1,
                  "pickupDate": "2026-02-20T10:00:00",
                  "expectedReturnDate": "2026-02-25T10:00:00"
                }
                """
)
public record RentalRequest(
        @NotNull(message = "Car ID cannot be null")
        @Schema(
                description = "The unique identifier of the car to be rented",
                example = "1",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Long carId,

        @NotNull(message = "Pickup date cannot be null")
        @FutureOrPresent(message = "Pickup date must be in the present or future")
        @Schema(
                description = "The date and time when the customer will pick up the rental car. Must be in the present or future (ISO 8601 format)",
                example = "2026-02-20T10:00:00",
                format = "date-time",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        LocalDateTime pickupDate,

        @NotNull(message = "Expected return date cannot be null")
        @FutureOrPresent(message = "Expected return date must be in the present or future")
        @Schema(
                description = "The expected date and time when the customer will return the rental car. Must be after the pickup date (ISO 8601 format)",
                example = "2026-02-25T10:00:00",
                format = "date-time",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        LocalDateTime expectedReturnDate
) {}
