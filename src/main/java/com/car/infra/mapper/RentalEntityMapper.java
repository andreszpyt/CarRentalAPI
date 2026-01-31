package com.car.infra.mapper;

import com.car.core.entities.Rental;
import com.car.infra.dtos.request.RentalRequest;
import com.car.infra.dtos.response.RentalResponse;

public class RentalEntityMapper {
    public Rental toRental(RentalRequest request){
        return new Rental(
                null,
                request.carId(),
                request.costumerId(),
                request.pickupDate(),
                request.expectedReturnDate(),
                null,
                null,
                null
        );
    }

    public RentalResponse toResponse(Rental rental){
        return new RentalResponse(
                rental.id(),
                rental.carId(),
                rental.costumerId(),
                rental.pickupDate(),
                rental.expectedReturnDate(),
                rental.actualReturnDate(),
                rental.totalValue(),
                rental.rentalStatus()
        );
    }
}
