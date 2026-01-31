package com.car.infra.mapper;

import com.car.core.entities.Customer;
import com.car.core.entities.vo.Cpf;
import com.car.core.entities.vo.Email;
import com.car.core.entities.vo.PhoneNumber;
import com.car.infra.dtos.request.CustomerRequest;
import com.car.infra.dtos.response.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest request) {
        return new Customer(
                null,
                request.name(),
                new Email(request.email()),
                request.password(),
                new Cpf(request.cpf()),
                new PhoneNumber(request.phoneNumber()),
                request.driverLicense(),
                request.birthDate()
        );
    }

    public CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.id(),
                customer.name(),
                customer.email().address(),
                customer.phoneNumber().value(),
                customer.driverLicense(),
                customer.birthDate()
        );
    }
}
