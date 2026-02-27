package com.car.core.usecases.customer.queries;

import com.car.core.entities.Customer;
import com.car.core.entities.vo.Cpf;
import com.car.core.entities.vo.Email;
import com.car.core.entities.vo.PhoneNumber;
import com.car.core.gateway.CustomerGateway;
import com.car.core.usecases.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FindByCpfUseCaseImplTest {
    @Mock
    private CustomerGateway customerGateway;

    @InjectMocks
    private FindByCpfUseCaseImpl findByCpfUseCase;

    @Test
    void shouldReturnCustomerWhenCpfExists(){
        Cpf cpf = new Cpf("69374123045");

        Customer expectedCustomer = new Customer(
                1L,
                "John Doe",
                new Email("john@example.com"),
                "hashedPass",
                cpf,
                new PhoneNumber("11999999999"),
                "12345AB",
                LocalDate.of(1990, 1, 1),
                "USER"
        );

        when(customerGateway.findByCpf(cpf.value())).thenReturn(Optional.of(expectedCustomer));

        Customer actualCustomer = findByCpfUseCase.execute(cpf.value());

        assertNotNull(actualCustomer);
        assertEquals(cpf.value(), actualCustomer.cpf().value());
        assertEquals(expectedCustomer, actualCustomer);
        verify(customerGateway, times(1)).findByCpf(cpf.value());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenCpfDoesNotExist() {
        Cpf cpf = new Cpf("69374123045");

        when(customerGateway.findByCpf(cpf.value())).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> findByCpfUseCase.execute(cpf.value()));
        assertEquals("Not Found costumer with cpf " + cpf.value(), exception.getMessage());
        verify(customerGateway, times(1)).findByCpf(cpf.value());
    }
}
