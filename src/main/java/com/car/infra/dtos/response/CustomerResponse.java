package com.car.infra.dtos.response;

import java.time.LocalDate;

public record CustomerResponse(
        Long id,
        String name,
        String email,
        String phoneNumber,
        String driverLicense,
        LocalDate birthDate
) {
}
