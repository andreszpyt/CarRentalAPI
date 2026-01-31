package com.car.infra.presentation;

import com.car.core.entities.Customer;
import com.car.core.usecases.customer.commands.RegisterCustomerUseCase;
import com.car.infra.dtos.request.CustomerRequest;
import com.car.infra.dtos.response.CustomerResponse;
import com.car.infra.mapper.CustomerMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final CustomerMapper mapper;
    private final RegisterCustomerUseCase registerCostumerUseCase;

    public UserController(CustomerMapper customerMapper, RegisterCustomerUseCase registerCostumerUseCase) {
        this.mapper = customerMapper;
        this.registerCostumerUseCase = registerCostumerUseCase;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> registerCustomer(@RequestBody CustomerRequest request) {
        Customer customer = registerCostumerUseCase.execute(mapper.toCustomer(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(customer));
    }
}
