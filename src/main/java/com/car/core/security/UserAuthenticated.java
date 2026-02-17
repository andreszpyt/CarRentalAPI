package com.car.core.security;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data Transfer Object (DTO) for authenticated user information.
 *
 * This record contains the JWT token and basic user information returned after
 * successful authentication. The token must be included in the Authorization header
 * for all subsequent requests to protected endpoints.
 *
 * @since 1.0
 * @author Car Rental API
 */
@Schema(
        name = "UserAuthenticated",
        title = "User Authenticated",
        description = "Response payload containing JWT token and authenticated user information"
)
public record UserAuthenticated(
        @Schema(
                description = "JWT bearer token for authenticating subsequent API requests. Include this token in the Authorization header as 'Bearer {token}'",
                example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
        )
        String token,

        @Schema(
                description = "The full name of the authenticated customer",
                example = "John Doe"
        )
        String name,

        @Schema(
                description = "The role/permission level of the authenticated user (e.g., USER, ADMIN)",
                example = "USER"
        )
        String role
) {
}
