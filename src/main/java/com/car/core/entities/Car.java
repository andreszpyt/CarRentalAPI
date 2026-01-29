package com.car.core.entities;

import com.car.core.entities.enums.CarClass;
import com.car.core.entities.enums.Category;

import java.math.BigDecimal;

public record Car(
         Long id,
         String brand,
         String model,
         Category category,
         CarClass carClass,
         String licensePlate,
         Integer year,
         String color,
         BigDecimal dailyRate,
         boolean available){
}
