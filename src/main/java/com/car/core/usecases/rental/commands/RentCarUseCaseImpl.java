package com.car.core.usecases.rental.commands;

import com.car.core.entities.Car;
import com.car.core.entities.Rental;
import com.car.core.entities.enums.RentalStatus;
import com.car.core.gateway.CarGateway;
import com.car.core.gateway.CustomerGateway;
import com.car.core.gateway.RentalGateway;
import com.car.core.usecases.exception.BusinessRuleException;
import com.car.core.usecases.exception.NotFoundException;

public class RentCarUseCaseImpl implements RentCarUseCase{
    private final RentalGateway rentalGateway;
    private final CarGateway carGateway;
    private final CustomerGateway customerGateway;

    public RentCarUseCaseImpl(RentalGateway rentalGateway, CarGateway carGateway, CustomerGateway customerGateway) {
        this.rentalGateway = rentalGateway;
        this.carGateway = carGateway;
        this.customerGateway = customerGateway;
    }

    @Override
    public Rental execute(Rental rental) {

        Car car = carGateway.findById(rental.carId())
                        .orElseThrow(() -> new NotFoundException("Car not found"));


        customerGateway.findById(rental.customerId())
                .orElseThrow(() -> new NotFoundException("Customer not exists!"));

        if(rentalGateway.hasConflictingRental(car.id(), rental.pickupDate(), rental.expectedReturnDate()) || !car.available()){
            throw new BusinessRuleException("Car already has rental");
        }

        long days = java.time.Duration.between(rental.pickupDate(), rental.expectedReturnDate()).toDays();
        if(days <= 0) throw new  BusinessRuleException("Days must be greater than 0");

        Rental newRental = new Rental(
                null,
                car.id(),
                rental.customerId(),
                rental.pickupDate(),
                rental.expectedReturnDate(),
                null,
                car.dailyRate().multiply(java.math.BigDecimal.valueOf(days)),
                RentalStatus.OPEN
        );


        return rentalGateway.createRental(newRental);
    }
}
