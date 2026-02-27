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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindByEmailUseCaseImplTest {
    @Mock
    private CustomerGateway customerGateway;

    @InjectMocks
    private FindByEmailUseCaseImpl findByEmailUseCase;

    @Test
    void shouldReturnCustomerWhenEmailExists(){
        Email email = new Email("john@example.com");

        Customer expectedCustomer = new Customer(
                1L,
                "John Doe",
                email,
                "hashedPass",
                new Cpf("69374123045"),
                new PhoneNumber("11999999999"),
                "12345AB",
                LocalDate.of(1990, 1, 1),
                "USER"
        );

        when(customerGateway.findByEmail(email.address())).thenReturn(java.util.Optional.of(expectedCustomer));
        Customer actualCustomer = findByEmailUseCase.execute(email.address());

        assertNotNull(actualCustomer);
        assertEquals(email.address(), actualCustomer.email().address());
        assertEquals(expectedCustomer, actualCustomer);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenEmailDoesNotExist() {
        Email email = new Email("john@example.com");

        when(customerGateway.findByEmail(email.address())).thenReturn(java.util.Optional.empty());
        NotFoundException exception = assertThrows(NotFoundException.class, () -> findByEmailUseCase.execute(email.address()));

        assertEquals("Not Found costumer with email " + email.address(), exception.getMessage());
    }
}
