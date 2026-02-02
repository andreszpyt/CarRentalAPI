package com.car.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;

public interface RentalRepository extends JpaRepository<RentalEntity, Long> {

    @Query("SELECT COUNT(r) > 0 FROM RentalEntity r " +
            "WHERE r.carId = :carId " +
            "AND r.status = 'OPEN' " +
            "AND (:pickup < r.expectedReturnDate AND :expected > r.pickupDate)")
    boolean hasConflictingRental(
            @Param("carId") Long carId,
            @Param("pickup") LocalDateTime pickup,
            @Param("expected") LocalDateTime expected
    );
}