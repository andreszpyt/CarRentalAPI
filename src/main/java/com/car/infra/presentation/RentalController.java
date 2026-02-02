package com.car.infra.presentation;

import com.car.core.entities.Rental;
import com.car.core.usecases.rental.RentCarUseCase;
import com.car.infra.dtos.request.RentalRequest;
import com.car.infra.dtos.response.RentalResponse;
import com.car.infra.mapper.RentalMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rental")
public class RentalController {

    private final RentalMapper mapper;
    private final RentCarUseCase rentCarUseCase;

    public RentalController(RentalMapper rentalMapper, RentCarUseCase rentCarUseCase) {
        this.mapper = rentalMapper;
        this.rentCarUseCase = rentCarUseCase;
    }

    @PostMapping
    public RentalResponse createRental(@RequestBody RentalRequest rentalRequest) {
        Rental rental = rentCarUseCase.execute(mapper.toRental(rentalRequest));
        return mapper.toResponse(rental);
    }
}
