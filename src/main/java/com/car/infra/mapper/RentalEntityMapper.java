package com.car.infra.mapper;

import com.car.core.entities.Rental;
import com.car.infra.persistence.RentalEntity;
import org.springframework.stereotype.Component;

@Component
public class RentalEntityMapper {
    public Rental toRental(RentalEntity entity){
        return new Rental(
                entity.getId(),
                entity.getCarId(),
                entity.getCustomerId(),
                entity.getPickupDate(),
                entity.getExpectedReturnDate(),
                entity.getActualReturnDate(),
                entity.getTotalValue(),
                entity.getStatus()
        );
    }

    public RentalEntity toEntity(Rental rental){
        return new RentalEntity(
                rental.id(),
                rental.carId(),
                rental.customerId(),
                rental.pickupDate(),
                rental.expectedReturnDate(),
                rental.actualReturnDate(),
                rental.totalValue(),
                rental.rentalStatus()
        );
    }
}
