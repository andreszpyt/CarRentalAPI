package com.car.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

    CarEntity findByLicensePlate(String licensePlate);
    List<CarEntity> findByCategory(String category);
}