package com.car.core.usecases.costumer.commands;

import com.car.core.entities.Costumer;
import com.car.core.gateway.CostumerGateway;
import com.car.core.security.PasswordEncryptor;
import com.car.core.usecases.exception.ConflictException;

public class RegisterCostumerUseCaseImpl implements RegisterCostumerUseCase {
    private final CostumerGateway costumerGateway;
    private final PasswordEncryptor passwordEncryptor;

    public RegisterCostumerUseCaseImpl(CostumerGateway costumerGateway, PasswordEncryptor passwordEncryptor) {
        this.costumerGateway = costumerGateway;
        this.passwordEncryptor = passwordEncryptor;
    }

    @Override
    public Costumer execute(Costumer costumer) {

        costumerGateway.findByCpf(costumer.cpf().value())
                .ifPresent(costumerEntity -> {throw new ConflictException("CPF already exists");});

        costumerGateway.findByEmail(costumer.email().address())
                .ifPresent(costumerEntity -> {throw new ConflictException("Email already exists");});

        String hashedPassword = passwordEncryptor.encrypt(costumer.password());

        Costumer secureCostumer = new Costumer(
                null,
                costumer.name(),
                costumer.email(),
                hashedPassword,
                costumer.cpf(),
                costumer.phoneNumber(),
                costumer.driverLicense(),
                costumer.birthDate()
        );


        return costumerGateway.registerCostumer(secureCostumer);
    }
}
