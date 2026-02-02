package com.car.infra.mapper;

import com.car.core.entities.Customer;
import com.car.core.entities.vo.Cpf;
import com.car.core.entities.vo.Email;
import com.car.core.entities.vo.PhoneNumber;
import com.car.infra.persistence.CustomerEntity;
import org.springframework.stereotype.Component;

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

    public Customer toCustomer(CustomerEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Customer(
                entity.getId(),
                entity.getName(),
                new Email(entity.getEmail()),
                entity.getPassword(),
                new Cpf(entity.getCpf()),
                new PhoneNumber(entity.getPhoneNumber()),
                entity.getDriverLicense(),
                entity.getBirthDate()
        );
    }
}
