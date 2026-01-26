package com.car.infra.persistence;

import com.car.core.entities.enums.Category;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private Category category;
    private String licensePlate;
    private Integer year;
    private String color;
    private BigDecimal dailyRate;
    private boolean available;
}
