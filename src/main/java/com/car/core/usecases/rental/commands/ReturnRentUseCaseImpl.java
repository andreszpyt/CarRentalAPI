package com.car.core.usecases.rental.commands;

import com.car.core.entities.Rental;
import com.car.core.entities.enums.RentalStatus;
import com.car.core.gateway.RentalGateway;
import com.car.core.usecases.exception.BusinessRuleException;
import com.car.core.usecases.exception.NotFoundException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReturnRentUseCaseImpl implements ReturnRentUseCase {
    private final RentalGateway rentalGateway;

    public ReturnRentUseCaseImpl(RentalGateway rentalGateway) {
        this.rentalGateway = rentalGateway;
    }

    @Override
    public Rental execute(Long rentalId) {
        Rental rental = rentalGateway.findRentalById(rentalId)
                .orElseThrow(() -> new NotFoundException("Rental not found!"));

        if(rental.rentalStatus() == RentalStatus.FINISHED){
            throw new BusinessRuleException("Rental is already finished!");
        }

        LocalDateTime now = LocalDateTime.now();
        long daysLate = java.time.Duration.between(rental.expectedReturnDate(), now).toDays();

        long penaltyDays = Math.max(0, daysLate);
        BigDecimal penaltyValue = BigDecimal.valueOf(penaltyDays * 400);

        Rental updateRent = new Rental(
                rental.id(),
                rental.carId(),
                rental.customerId(),
                rental.pickupDate(),
                rental.expectedReturnDate(),
                now,
                rental.totalValue().add(penaltyValue),
                rental.rentalStatus()
        );

        return rentalGateway.createRental(updateRent);
    }
}
