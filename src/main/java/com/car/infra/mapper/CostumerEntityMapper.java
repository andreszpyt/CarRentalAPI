package com.car.infra.mapper;

import com.car.core.entities.Costumer;
import com.car.core.entities.vo.Cpf;
import com.car.core.entities.vo.Email;
import com.car.core.entities.vo.PhoneNumber;
import com.car.infra.persistence.CostumerEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CostumerEntityMapper {
    public CostumerEntity toEntity(Costumer costumer) {
        return new CostumerEntity(
                costumer.id(),
                costumer.name(),
                costumer.email().address(),
                costumer.password(),
                costumer.cpf().value(),
                costumer.phoneNumber().value(),
                costumer.driverLicense(),
                costumer.birthDate()
        );
    }

    public Costumer toCostumer(Optional<CostumerEntity> costumerEntity) {
        return new Costumer(
                costumerEntity.getId(),
                costumerEntity.getName(),
                new Email(costumerEntity.getEmail()),
                costumerEntity.getPassword(),
                new Cpf(costumerEntity.getCpf()),
                new PhoneNumber(costumerEntity.getPhoneNumber()),
                costumerEntity.getDriverLicense(),
                costumerEntity.getBirthDate()
        );
    }
}
