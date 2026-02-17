package com.car.infra.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for customer response data.
 *
 * This record contains the customer information returned from the API,
 * including personal details and identification information.
 *
 * @since 1.0
 * @author Car Rental API
 */
@Schema(
        name = "CustomerResponse",
        title = "Customer Response",
        description = "Response payload containing customer information from the rental system"
)
public record CustomerResponse(
        @Schema(
                description = "The unique identifier of the customer in the system",
                example = "1"
        )
        Long id,

        @Schema(
                description = "The full name of the customer",
                example = "John Doe"
        )
        String name,

        @Schema(
                description = "The customer's email address",
                example = "john.doe@example.com"
        )
        String email,

        @Schema(
                description = "The customer's phone number for contact",
                example = "+55 11 98765-4321"
        )
        String phoneNumber,

        @Schema(
                description = "The customer's driver license number",
                example = "1234567890"
        )
        String driverLicense,

        @Schema(
                description = "The customer's date of birth in ISO 8601 format (YYYY-MM-DD)",
                example = "1990-05-15",
                format = "date"
        )
        LocalDate birthDate
) {
}
