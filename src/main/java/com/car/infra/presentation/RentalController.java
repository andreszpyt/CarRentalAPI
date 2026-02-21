package com.car.infra.presentation;

import com.car.core.entities.Rental;
import com.car.core.usecases.rental.commands.RentCarUseCase;
import com.car.core.usecases.rental.commands.ReturnRentUseCase;
import com.car.core.usecases.rental.queries.FindRentByCustomerUseCase;
import com.car.infra.dtos.request.RentalRequest;
import com.car.infra.dtos.response.RentalResponse;
import com.car.infra.mapper.RentalMapper;
import com.car.infra.persistence.CustomerEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing car rentals in the rental system.
 *
 * This controller handles all rental-related operations including creation of rentals,
 * processing returns, and retrieving rental history for customers. Requires authentication.
 *
 * @since 1.0
 * @author Car Rental API
 */
@RestController
@RequestMapping("/rental")
@Tag(
        name = "Rental Management",
        description = "Endpoints for managing car rentals. Includes operations for creating rentals, processing returns, and retrieving rental history. All endpoints require authentication."
)
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
    @Operation(
            summary = "Create a new car rental",
            description = "Creates a new car rental reservation for the authenticated customer. Assigns the rental to the customer and sets the pickup and expected return dates.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Rental successfully created",
                    content = @Content(schema = @Schema(implementation = RentalResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request body - validation failed"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - authentication required"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Car not found"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @Transactional
    public RentalResponse createRental(
            @RequestBody RentalRequest rentalRequest,
            @AuthenticationPrincipal CustomerEntity customerEntity) {
        Rental rental = rentCarUseCase.execute(mapper.toRental(rentalRequest, customerEntity.getId()));
        return mapper.toResponse(rental);
    }

    @PostMapping("/{id}/return")
    @Operation(
            summary = "Process rental return",
            description = "Processes the return of a rented car. Updates the rental status and calculates any additional charges if the return is late.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Rental return successfully processed",
                    content = @Content(schema = @Schema(implementation = RentalResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - authentication required"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Rental with the specified ID not found"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @Transactional
    public ResponseEntity<RentalResponse> returnRental(
            @PathVariable
            @Parameter(description = "The unique identifier of the rental to return")
            Long id) {
        Rental rental = returnRentUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(rental));
    }

    @GetMapping("/me/all")
    @Operation(
            summary = "Retrieve all rentals for the authenticated customer",
            description = "Retrieves the complete rental history for the authenticated customer, including active and completed rentals.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Customer rental history successfully retrieved",
                    content = @Content(schema = @Schema(implementation = RentalResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - authentication required"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Customer not found"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @Transactional
    public ResponseEntity<List<RentalResponse>> allRentals(
            @AuthenticationPrincipal CustomerEntity customer) {
        List<RentalResponse> response = findRentByCustomerUseCase.execute(customer.getId()).stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
