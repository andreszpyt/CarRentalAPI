package com.car.core.usecases.customer.commands;

import com.car.core.entities.Customer;
import com.car.core.entities.vo.Cpf;
import com.car.core.entities.vo.Email;
import com.car.core.entities.vo.PhoneNumber;
import com.car.core.gateway.CustomerGateway;
import com.car.core.security.PasswordEncryptor;
import com.car.core.usecases.exception.ConflictException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegisterCustomerUseCaseImplTest {

    @Mock
    private CustomerGateway customerGateway;

    @Mock
    private PasswordEncryptor passwordEncryptor;

    @InjectMocks
    private RegisterCustomerUseCaseImpl registerCustomerUseCase;

    @Test
    @DisplayName("Should register customer successfully")
    void shouldRegisterCustomerSuccessfully(){
        Cpf cpf = new Cpf("69374123045");
        Email email = new Email("test@domain.com");
        PhoneNumber phone = new PhoneNumber("11999999999");

        Customer customerToRegister = new Customer(
                null, "John Doe", email, "plainPassword", cpf,
                phone, "12345AB", LocalDate.of(1990, 1, 1), null
        );

        String hashedPassword = "hashedPassword";

        when(customerGateway.findByCpf(cpf.value())).thenReturn(Optional.empty());
        when(customerGateway.findByEmail(email.address())).thenReturn(Optional.empty());

        when(passwordEncryptor.encrypt("plainPassword")).thenReturn(hashedPassword);

        Customer savedCustomer = new Customer(
                1L, "John Doe", email, hashedPassword, cpf,
                phone, "12345AB", LocalDate.of(1990, 1, 1), "USER"
        );
        when(customerGateway.registerCustomer(any(Customer.class))).thenReturn(savedCustomer);

        Customer result = registerCustomerUseCase.execute(customerToRegister);

        assertNotNull(result);

        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerGateway).registerCustomer(customerCaptor.capture());

        Customer capturedCustomer = customerCaptor.getValue();
        assertEquals(hashedPassword, capturedCustomer.password());
        assertEquals("USER", capturedCustomer.role());

        verify(customerGateway, times(1)).findByCpf(cpf.value());
        verify(customerGateway, times(1)).findByEmail(email.address());
    }

    @Test
    void shouldThrowConflictExceptionWhenCpfAlreadyExists(){
        Cpf cpf = new Cpf("69374123045");
        Email email = new Email("test@domain.com");
        PhoneNumber phone = new PhoneNumber("11999999999");

        Customer customerToRegister = new Customer(
                null, "John Doe", email, "plainPassword", cpf,
                phone, "12345AB", LocalDate.of(1990, 1, 1), null
        );

        when(customerGateway.findByCpf(cpf.value())).thenReturn(Optional.of(customerToRegister));
        ConflictException exception = assertThrows(ConflictException.class, () -> registerCustomerUseCase.execute(customerToRegister));

        assertEquals("CPF already exists", exception.getMessage());
        verify(customerGateway, times(1)).findByCpf(cpf.value());
        verify(customerGateway, times(0)).findByEmail(anyString());
        verify(customerGateway, times(0)).registerCustomer(any(Customer.class));
    }

    @Test
    void shouldThrowConflictExceptionWhenEmailAlreadyExists() {
        Cpf cpf = new Cpf("69374123045");
        Email email = new Email("test@domain.com");
        PhoneNumber phone = new PhoneNumber("11999999999");

        Customer customerToRegister = new Customer(
                null, "John Doe", email, "plainPassword", cpf,
                phone, "12345AB", LocalDate.of(1990, 1, 1), null
        );

        when(customerGateway.findByCpf(cpf.value())).thenReturn(Optional.empty());
        when(customerGateway.findByEmail(email.address())).thenReturn(Optional.of(customerToRegister));

        ConflictException exception = assertThrows(ConflictException.class, () -> registerCustomerUseCase.execute(customerToRegister));

        assertEquals("Email already exists", exception.getMessage());
        verify(customerGateway, times(1)).findByCpf(cpf.value());
        verify(customerGateway, times(1)).findByEmail(email.address());
        verify(customerGateway, times(0)).registerCustomer(any(Customer.class));
    }
}