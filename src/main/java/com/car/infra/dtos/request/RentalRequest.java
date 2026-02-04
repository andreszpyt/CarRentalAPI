package com.car.infra.dtos.request;


import java.time.LocalDateTime;

public record RentalRequest(
        Long carId,
        LocalDateTime pickupDate,
        LocalDateTime expectedReturnDate) {
}
