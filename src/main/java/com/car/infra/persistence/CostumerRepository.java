package com.car.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CostumerRepository extends JpaRepository<CostumerEntity, Long> {
}
