package com.car.core.usecases.rental.queries;

import com.car.core.entities.Rental;
import com.car.core.gateway.RentalGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindRentByIdUseCaseImplTest {
    @Mock
    private RentalGateway rentalGateway;

    @InjectMocks
    private FindRentByIdUseCaseImpl findRentByIdUseCase;

    @Test
    void shouldReturnRentalSuccessfullyWhenItExists() {
        Rental rental = mock(Rental.class);
        when(rentalGateway.findRentalById(1L)).thenReturn(Optional.of(rental));
        Rental result = findRentByIdUseCase.execute(1L);
        assertEquals(rental, result);
    }

    @Test
    void shouldThrowExceptionWhenRentalNotFound() {
        when(rentalGateway.findRentalById(1L)).thenReturn(Optional.empty());
        try {
            findRentByIdUseCase.execute(1L);
        } catch (Exception e) {
            assertEquals("Rental not found.", e.getMessage());
        }
    }
}
