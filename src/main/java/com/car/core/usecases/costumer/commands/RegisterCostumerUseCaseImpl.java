package com.car.core.usecases.costumer.commands;

import com.car.core.entities.Costumer;
import com.car.core.gateway.CostumerGateway;
import com.car.core.usecases.exception.ConflictException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class RegisterCostumerUseCaseImpl implements RegisterCostumerUseCase {
    private final CostumerGateway costumerGateway;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public RegisterCostumerUseCaseImpl(CostumerGateway costumerGateway, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.costumerGateway = costumerGateway;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Costumer execute(Costumer costumer) {

        costumerGateway.findByCpf(costumer.cpf().value())
                .ifPresent(costumerEntity -> {throw new ConflictException("CPF already exists");});

        costumerGateway.findByEmail(costumer.email().address())
                .ifPresent(costumerEntity -> {throw new ConflictException("Email already exists");});

        String hashedPassword = bCryptPasswordEncoder.encode(costumer.password());

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
