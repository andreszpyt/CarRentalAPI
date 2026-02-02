package com.car.core.entities;

import com.car.core.entities.enums.RentalStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Rental(
        Long id,
        Long carId,
        Long customerId,
        LocalDateTime pickupDate,
        LocalDateTime expectedReturnDate,
        LocalDateTime actualReturnDate,
        BigDecimal totalValue,
        RentalStatus rentalStatus
) {
    public BigDecimal getTotalValue(BigDecimal dailyRate, long days) {
        return dailyRate.multiply(new BigDecimal(days));
    }
}
