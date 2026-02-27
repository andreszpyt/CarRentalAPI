package com.car.core.usecases.rental.queries;

import com.car.core.entities.Customer;
import com.car.core.entities.Rental;
import com.car.core.gateway.CustomerGateway;
import com.car.core.gateway.RentalGateway;
import com.car.core.usecases.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindRentByCustomerUseCaseImplTest {
    @Mock
    private RentalGateway rentalGateway;

    @Mock
    private CustomerGateway customerGateway;

    @InjectMocks
    private FindRentByCustomerUseCaseImpl findRentByCustomerUseCase;

    @Test
    void shouldReturnListOfRentalsWhenCustomerExists() {
        Long customerId = 1L;
        Customer customer = mock(Customer.class);
        when(customerGateway.findById(customerId)).thenReturn(Optional.of(customer));
        List<Rental> rentals = Arrays.asList(mock(Rental.class), mock(Rental.class));
        when(rentalGateway.findRentalByCustomer(customerId)).thenReturn(rentals);

        List<Rental> result = findRentByCustomerUseCase.execute(customerId);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenCustomerDoesNotExist() {
        Long customerId = 1L;
        when(customerGateway.findById(customerId)).thenReturn(Optional.empty());

        NotFoundException exception = org.junit.jupiter.api.Assertions.assertThrows(NotFoundException.class, () -> findRentByCustomerUseCase.execute(customerId));

        assertEquals("Customer not found", exception.getMessage());
    }

    @Test
    void shouldReturnEmptyListWhenCustomerHasNoRentals() {
        Long customerId = 1L;
        Customer customer = mock(Customer.class);
        when(customerGateway.findById(customerId)).thenReturn(Optional.of(customer));
        when(rentalGateway.findRentalByCustomer(customerId)).thenReturn(List.of());

        List<Rental> result = findRentByCustomerUseCase.execute(customerId);

        assertNotNull(result);
        assertEquals(0, result.size());
    }

}


