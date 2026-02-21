package com.car.core.usecases.car.commands;

import com.car.core.entities.Car;
import com.car.core.gateway.CarGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DeleteCarUseCaseImplTest {

    @Mock
    private CarGateway carGateway;

    @InjectMocks
    private DeleteCarUseCaseImpl deleteCarUseCase;

    @Test
    void shouldDeleteCarSuccessfullyWhenCarExists() {
        Long carId = 1L;
        Car existingCar = new Car(carId, "Toyota", "Corolla", null, null, "ABC-1234", 2024, "Silver", null, true);

        Mockito.when(carGateway.findById(carId)).thenReturn(Optional.of(existingCar));

        deleteCarUseCase.execute(carId);

        Mockito.verify(carGateway, Mockito.times(1)).findById(carId);
        Mockito.verify(carGateway, Mockito.times(1)).deleteCar(carId);
    }

    @Test
    void shouldThrowExceptionWhenCarDoesNotExist() {
        Long carId = 1L;

        Mockito.when(carGateway.findById(carId)).thenReturn(Optional.empty());

        org.junit.jupiter.api.Assertions.assertThrows(com.car.core.usecases.exception.NotFoundException.class, () -> deleteCarUseCase.execute(carId));

        Mockito.verify(carGateway, Mockito.times(1)).findById(carId);
        Mockito.verify(carGateway, Mockito.never()).deleteCar(carId);
    }
}
