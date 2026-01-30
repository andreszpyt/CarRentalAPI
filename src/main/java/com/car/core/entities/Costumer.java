package com.car.core.entities;

import com.car.core.entities.vo.Cpf;
import com.car.core.entities.vo.Email;
import com.car.core.entities.vo.PhoneNumber;

import java.time.LocalDate;

public record Costumer(
        Long id,
        String name,
        Email email,
        String password,
        Cpf cpf,
        PhoneNumber phoneNumber,
        String driverLicense,
        LocalDate birthDate
) {
}
