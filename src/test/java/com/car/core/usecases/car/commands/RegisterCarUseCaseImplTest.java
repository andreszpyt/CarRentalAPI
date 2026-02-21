package com.car.core.usecases.car.commands;


import com.car.core.entities.Car;
import com.car.core.gateway.CarGateway;
import com.car.core.usecases.exception.ConflictException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class RegisterCarUseCaseImplTest {

    @Mock
    private CarGateway carGateway;

    @InjectMocks
    private RegisterCarUseCaseImpl registerCarUseCase;

    @Test
    void shouldRegisterCarSuccessfully() {
        Long carId = 1L;
        Car carToRegister = new Car(null, "Toyota", "Corolla", null, null, "ABC-1234", 2024, "Silver", null, true);
        Car registeredCar = new Car(carId, "Toyota", "Corolla", null, null, "ABC-1234", 2024, "Silver", null, true);

        Mockito.when(carGateway.findCarByPlate("ABC-1234")).thenReturn(Optional.empty());

        Mockito.when(carGateway.registerCar(carToRegister)).thenReturn(registeredCar);

        Car actualCar = registerCarUseCase.execute(carToRegister);

        assertNotNull(actualCar);
        assertEquals(registeredCar, actualCar);

        Mockito.verify(carGateway, Mockito.times(1)).findCarByPlate("ABC-1234");
        Mockito.verify(carGateway, Mockito.times(1)).registerCar(carToRegister);
    }

    @Test
    void shouldThrowExceptionWhenPlateAlreadyExists() {
        Long carId = 1L;
        Car carToRegister = new Car(null, "Toyota", "Corolla", null, null, "ABC-1234", 2024, "Silver", null, true);
        Car existingCar = new Car(carId, "Toyota", "Corolla", null, null, "ABC-1234", 2024, "Silver", null, true);

        Mockito.when(carGateway.findCarByPlate("ABC-1234")).thenReturn(Optional.of(existingCar));
        ConflictException exception = org.junit.jupiter.api.Assertions.assertThrows(ConflictException.class, () -> registerCarUseCase.execute(carToRegister));
        assertEquals("Plate alredy exists: ABC-1234", exception.getMessage());
        Mockito.verify(carGateway, Mockito.times(1)).findCarByPlate("ABC-1234");
        Mockito.verify(carGateway, Mockito.never()).registerCar(carToRegister);    }
}
