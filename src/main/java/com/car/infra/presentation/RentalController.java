package com.car.infra.presentation;

import com.car.core.entities.Rental;
import com.car.core.usecases.rental.commands.RentCarUseCase;
import com.car.core.usecases.rental.commands.ReturnRentUseCase;
import com.car.core.usecases.rental.queries.FindRentByCustomerUseCase;
import com.car.infra.dtos.request.RentalRequest;
import com.car.infra.dtos.response.RentalResponse;
import com.car.infra.mapper.RentalMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {

    private final RentalMapper mapper;
    private final RentCarUseCase rentCarUseCase;
    private final ReturnRentUseCase  returnRentUseCase;
    private final FindRentByCustomerUseCase findRentByCustomerUseCase;

    public RentalController(RentalMapper mapper, RentCarUseCase rentCarUseCase, ReturnRentUseCase returnRentUseCase, FindRentByCustomerUseCase findRentByCustomerUseCase) {
        this.mapper = mapper;
        this.rentCarUseCase = rentCarUseCase;
        this.returnRentUseCase = returnRentUseCase;
        this.findRentByCustomerUseCase = findRentByCustomerUseCase;
    }

    @PostMapping
    public RentalResponse createRental(@RequestBody RentalRequest rentalRequest) {
        Rental rental = rentCarUseCase.execute(mapper.toRental(rentalRequest));
        return mapper.toResponse(rental);
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<RentalResponse> returnRental(@PathVariable Long id) {
        Rental rental = returnRentUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(rental));
    }

    @GetMapping("{id}/all")
    public ResponseEntity<List<RentalResponse>> allRentals(@PathVariable Long id) {
        List<RentalResponse> response = findRentByCustomerUseCase.execute(id).stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
