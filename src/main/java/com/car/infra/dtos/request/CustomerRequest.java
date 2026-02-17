package com.car.infra.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for customer registration and update requests.
 *
 * This record contains all the necessary information to create or update a customer
 * in the rental system, including personal information, authentication credentials,
 * and identification documents.
 *
 * @since 1.0
 * @author Car Rental API
 */
@Schema(
        name = "CustomerRequest",
        title = "Customer Request",
        description = "Request payload for creating or updating a customer in the rental system",
        example = """
                {
                  "name": "John Doe",
                  "email": "john.doe@example.com",
                  "password": "SecurePass123",
                  "cpf": "12345678900",
                  "phoneNumber": "+55 11 98765-4321",
                  "driverLicense": "1234567890",
                  "birthDate": "1990-05-15"
                }
                """
)
public record CustomerRequest(
        @NotBlank(message = "Name is required")
        @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
        @Schema(
                description = "The full name of the customer",
                example = "John Doe",
                minLength = 3,
                maxLength = 100,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String name,

        @Email(message = "Invalid email format")
        @NotBlank(message = "Email is required")
        @Size(min = 5, max = 100, message = "Email must be between 5 and 100 characters")
        @Schema(
                description = "The customer's email address used for authentication and communications",
                example = "john.doe@example.com",
                minLength = 5,
                maxLength = 100,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
        @Schema(
                description = "The customer's password for account authentication. Must be at least 8 characters long",
                example = "SecurePass123",
                minLength = 8,
                maxLength = 100,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String password,

        @NotBlank(message = "CPF is required")
        @Size(min = 11, max = 11, message = "CPF must have exactly 11 characters")
        @Pattern(regexp = "^[0-9]{11}$", message = "CPF must contain only 11 digits")
        @Schema(
                description = "The customer's CPF (Cadastro de Pessoas Físicas) - Brazilian tax identification number. Must be 11 digits",
                example = "12345678900",
                pattern = "^[0-9]{11}$",
                minLength = 11,
                maxLength = 11,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String cpf,

        @Size(min = 10, max = 20, message = "Phone number must be between 10 and 20 characters")
        @Schema(
                description = "The customer's phone number for contact purposes",
                example = "+55 11 98765-4321",
                minLength = 10,
                maxLength = 20,
                requiredMode = Schema.RequiredMode.NOT_REQUIRED
        )
        String phoneNumber,

        @NotBlank(message = "Driver license is required")
        @Size(min = 5, max = 20, message = "Driver license must be between 5 and 20 characters")
        @Schema(
                description = "The customer's driver license number for rental eligibility verification",
                example = "1234567890",
                minLength = 5,
                maxLength = 20,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String driverLicense,

        @NotNull(message = "Birth date is required")
        @Schema(
                description = "The customer's date of birth in ISO 8601 format (YYYY-MM-DD). Used to verify minimum age requirements for car rental",
                example = "1990-05-15",
                format = "date",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        LocalDate birthDate
) {}
