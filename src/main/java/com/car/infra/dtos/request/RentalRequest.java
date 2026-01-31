package com.car.infra.dtos.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RentalRequest(
        Long carId,
        Long costumerId,
        LocalDateTime pickupDate,
        LocalDateTime expectedReturnDate,
        @NotNull(message = "Rental status type is required")
        RentalRequest rentalStatus
) {
}
