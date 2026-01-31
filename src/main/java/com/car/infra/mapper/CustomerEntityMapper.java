package com.car.infra.mapper;

import com.car.core.entities.Customer;
import com.car.core.entities.vo.Cpf;
import com.car.core.entities.vo.Email;
import com.car.core.entities.vo.PhoneNumber;
import com.car.infra.persistence.CustomerEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerEntityMapper {
    public CustomerEntity toEntity(Customer costumer) {
        return new CustomerEntity(
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

    public Customer toCostumer(Optional<CustomerEntity> costumerEntity) {

        return new Customer(
                costumerEntity.get().getId(),
                costumerEntity.get().getName(),
                new Email(costumerEntity.get().getEmail()),
                costumerEntity.get().getPassword(),
                new Cpf(costumerEntity.get().getCpf()),
                new PhoneNumber(costumerEntity.get().getPhoneNumber()),
                costumerEntity.get().getDriverLicense(),
                costumerEntity.get().getBirthDate()
        );
    }
}
