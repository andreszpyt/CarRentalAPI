package com.car.infra.persistence;

import com.car.core.entities.enums.CarClass;
import com.car.core.entities.enums.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Cars")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private CarClass carClass;
    private String licensePlate;
    @Column(name = "year_car")
    private Integer year;
    private String color;
    private BigDecimal dailyRate;
    private boolean available;
}
