package com.car.infra.dtos.response;

import com.car.core.entities.enums.RentalStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RentalResponse(
        Long id,
        Long carId,
        Long costumerId,
        LocalDateTime pickupDate,
        LocalDateTime expectedReturnDate,
        LocalDateTime actualReturnDate,
        BigDecimal totalValue,
        RentalStatus rentalStatus
) {
}
