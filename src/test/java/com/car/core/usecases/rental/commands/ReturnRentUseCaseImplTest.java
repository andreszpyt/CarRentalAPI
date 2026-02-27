package com.car.core.usecases.rental.commands;

import com.car.core.entities.Rental;
import com.car.core.entities.enums.RentalStatus;
import com.car.core.gateway.RentalGateway;
import com.car.core.usecases.exception.BusinessRuleException;
import com.car.core.usecases.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReturnRentUseCaseImplTest {

    @Mock
    private RentalGateway rentalGateway;

    private ReturnRentUseCaseImpl returnRentUseCase;

    private final BigDecimal PENALTY_AMOUNT = BigDecimal.valueOf(50);

    @BeforeEach
    void setUp() {
        returnRentUseCase = new ReturnRentUseCaseImpl(rentalGateway, PENALTY_AMOUNT);
    }

    @Test
    @DisplayName("Should return car successfully on time")
    void shouldReturnCarSuccessfullyOnTime() {
        Long rentalId = 1L;

        Rental openRental = new Rental(
                rentalId,
                1L,
                1L,
                LocalDateTime.now().minusDays(5),
                LocalDateTime.now().plusDays(5),
                null,
                BigDecimal.valueOf(100),
                RentalStatus.OPEN
        );

        when(rentalGateway.findRentalById(rentalId)).thenReturn(Optional.of(openRental));

        returnRentUseCase.execute(rentalId);

        ArgumentCaptor<Rental> rentalCaptor = ArgumentCaptor.forClass(Rental.class);

        verify(rentalGateway, times(1)).createRental(rentalCaptor.capture());

        Rental savedRental = rentalCaptor.getValue();

        assertEquals(RentalStatus.FINISHED, savedRental.rentalStatus());
        assertNotNull(savedRental.actualReturnDate());
        assertEquals(BigDecimal.valueOf(100), savedRental.totalValue());
    }

    @Test
    void shouldThrowExceptionWhenRentalNotFound(){
        Long rentalId = 1L;

        when(rentalGateway.findRentalById(rentalId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> returnRentUseCase.execute(rentalId));
        assertEquals("Rental not found!", exception.getMessage());
    }

    @Test
    void shouldApplyPenaltyWhenReturningLate() {
        Long rentalId = 1L;

        Rental openRental = new Rental(
                rentalId,
                1L,
                1L,
                LocalDateTime.now().minusDays(10),
                LocalDateTime.now().minusDays(5),
                null,
                BigDecimal.valueOf(100),
                RentalStatus.OPEN
        );

        when(rentalGateway.findRentalById(rentalId)).thenReturn(Optional.of(openRental));

        returnRentUseCase.execute(rentalId);

        ArgumentCaptor<Rental> rentalCaptor = ArgumentCaptor.forClass(Rental.class);

        verify(rentalGateway, times(1)).createRental(rentalCaptor.capture());

        Rental savedRental = rentalCaptor.getValue();

        assertEquals(RentalStatus.FINISHED, savedRental.rentalStatus());
        assertNotNull(savedRental.actualReturnDate());
        assertEquals(BigDecimal.valueOf(350), savedRental.totalValue());
    }

    @Test
    void shouldThrowExceptionWhenRentalIsAlreadyFinished() {
        Long rentalId = 1L;

        Rental finishedRental = new Rental(
                rentalId,
                1L,
                1L,
                LocalDateTime.now().minusDays(10),
                LocalDateTime.now().minusDays(5),
                LocalDateTime.now().minusDays(4),
                BigDecimal.valueOf(100),
                RentalStatus.FINISHED
        );

        when(rentalGateway.findRentalById(rentalId)).thenReturn(Optional.of(finishedRental));

        BusinessRuleException exception = assertThrows(BusinessRuleException.class, () -> returnRentUseCase.execute(rentalId));
        assertEquals("Rental is already finished!", exception.getMessage());
    }
}