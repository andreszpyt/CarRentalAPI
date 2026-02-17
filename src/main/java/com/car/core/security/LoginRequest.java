package com.car.core.security;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) for user login requests.
 *
 * This record contains the authentication credentials required for customer login.
 * The email and password are validated against the registered customer accounts.
 *
 * @since 1.0
 * @author Car Rental API
 */
@Schema(
        name = "LoginRequest",
        title = "Login Request",
        description = "Request payload for customer authentication and JWT token generation"
)
public record LoginRequest(
        @Email(message = "Invalid email format")
        @NotBlank(message = "Email is required")
        @Schema(
                description = "The customer's registered email address used for authentication",
                example = "john.doe@example.com",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String email,

        @NotBlank(message = "Password is required")
        @Schema(
                description = "The customer's account password",
                example = "SecurePass123",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String password
) {
}
