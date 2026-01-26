package com.car.infra.dtos.response;

import com.car.core.entities.enums.Category;

import java.math.BigDecimal;

public record CarResponse (
        Long carId,
        String brand,
        String model,
        Category category,
        String licensePlate,
        Integer year,
        String color,
        BigDecimal dailyRate,
        boolean available
){ }
