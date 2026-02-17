package com.car.infra.dtos.response;

import com.car.core.entities.enums.RentalStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for rental response data.
 *
 * This record contains the complete rental information returned from the API,
 * including rental period, customer and car references, pricing, and status.
 *
 * @since 1.0
 * @author Car Rental API
 */
@Schema(
        name = "RentalResponse",
        title = "Rental Response",
        description = "Response payload containing rental reservation information from the rental system"
)
public record RentalResponse(
        @Schema(
                description = "The unique identifier of the rental in the system",
                example = "1"
        )
        Long id,

        @Schema(
                description = "The unique identifier of the rented car",
                example = "1"
        )
        Long carId,

        @Schema(
                description = "The unique identifier of the customer who made the rental",
                example = "1"
        )
        Long customerId,

        @Schema(
                description = "The date and time when the customer will pick up the rental car (ISO 8601 format)",
                example = "2026-02-20T10:00:00",
                format = "date-time"
        )
        LocalDateTime pickupDate,

        @Schema(
                description = "The expected date and time when the customer will return the rental car (ISO 8601 format)",
                example = "2026-02-25T10:00:00",
                format = "date-time"
        )
        LocalDateTime expectedReturnDate,

        @Schema(
                description = "The actual date and time when the customer returned the rental car. Null if rental is still active",
                example = "2026-02-25T10:00:00",
                format = "date-time"
        )
        LocalDateTime actualReturnDate,

        @Schema(
                description = "The total value of the rental including any late fees or additional charges",
                example = "449.50"
        )
        BigDecimal totalValue,

        @Schema(
                description = "The current status of the rental (ACTIVE, COMPLETED, CANCELLED)",
                example = "ACTIVE"
        )
        RentalStatus rentalStatus
) {
}
