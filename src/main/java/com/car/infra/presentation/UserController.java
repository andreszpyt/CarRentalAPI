package com.car.infra.presentation;

import com.car.core.entities.Customer;
import com.car.core.security.LoginRequest;
import com.car.core.security.UserAuthenticated;
import com.car.core.usecases.customer.auth.AuthenticateUserUseCase;
import com.car.core.usecases.customer.commands.RegisterCustomerUseCase;
import com.car.infra.dtos.request.CustomerRequest;
import com.car.infra.dtos.response.CustomerResponse;
import com.car.infra.mapper.CustomerMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for user authentication and customer registration.
 *
 * This controller handles user authentication operations including customer registration
 * and login functionality. Provides JWT token generation for authenticated users.
 *
 * @since 1.0
 * @author Car Rental API
 */
@RestController
@RequestMapping("/user")
@Tag(
        name = "User Authentication",
        description = "Endpoints for user authentication including customer registration and login. Provides JWT tokens for securing other API operations."
)
public class UserController {

    private final CustomerMapper mapper;
    private final RegisterCustomerUseCase registerCostumerUseCase;
    private final AuthenticateUserUseCase authenticateUserUseCase;

    public UserController(CustomerMapper mapper, RegisterCustomerUseCase registerCostumerUseCase, AuthenticateUserUseCase authenticateUserUseCase) {
        this.mapper = mapper;
        this.registerCostumerUseCase = registerCostumerUseCase;
        this.authenticateUserUseCase = authenticateUserUseCase;
    }

    @PostMapping("/register")
    @Operation(
            summary = "Register a new customer",
            description = "Creates a new customer account in the system. The customer can then use the login endpoint to authenticate and receive a JWT token."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Customer successfully registered",
                    content = @Content(schema = @Schema(implementation = CustomerResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request body - validation failed or customer already exists"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @Transactional
    public ResponseEntity<CustomerResponse> registerCustomer(@RequestBody CustomerRequest request) {
        Customer customer = registerCostumerUseCase.execute(mapper.toCustomer(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(customer));
    }

    @PostMapping("/login")
    @Operation(
            summary = "Authenticate customer and obtain JWT token",
            description = "Authenticates a customer using their email and password credentials. Returns a JWT token that must be included in the Authorization header for accessing protected endpoints."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Authentication successful - JWT token generated",
                    content = @Content(schema = @Schema(implementation = UserAuthenticated.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request body"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Invalid email or password - authentication failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @Transactional
    public ResponseEntity<UserAuthenticated> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticateUserUseCase.execute(loginRequest));
    }
}
