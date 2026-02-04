package com.car.infra.mapper;

import com.car.core.entities.Rental;
import com.car.infra.dtos.request.RentalRequest;
import com.car.infra.dtos.response.RentalResponse;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper {

    private final CustomerMapper customerMapper;

    public RentalMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public Rental toRental(RentalRequest request, Long customerId){
        return new Rental(
                null,
                request.carId(),
                customerId,
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
                rental.customerId(),
                rental.pickupDate(),
                rental.expectedReturnDate(),
                null,
                rental.totalValue(),
                rental.rentalStatus()
        );
    }
}
