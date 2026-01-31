package com.car.infra.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CustomerRequest(
        @NotBlank(message = "Name is required")
        String name,
        @Email(message = "Invalid email format")
        @NotBlank(message = "Email is required")
        String email,
        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters")
        String password,
        @NotBlank(message = "CPF is required")
        String cpf,
        String phoneNumber,
        @NotBlank(message = "Driver license is required")
        String driverLicense,
        @NotNull(message = "Birth date is required")
        LocalDate birthDate
) {}
