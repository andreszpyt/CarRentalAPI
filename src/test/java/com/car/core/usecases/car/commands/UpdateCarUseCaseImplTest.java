package com.car.core.usecases.car.commands;

import com.car.core.entities.Car;
import com.car.core.entities.enums.CarClass;
import com.car.core.entities.enums.Category;
import com.car.core.gateway.CarGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class UpdateCarUseCaseImplTest {

    @Mock
    private CarGateway carGateway;

    @InjectMocks
    private UpdateCarUseCaseImpl updateCarUseCase;

    @Test
    void shouldUpdateCarSuccessfully() {
        Long carId = 1L;
        Car carToUpdate = new Car(null, "Toyota", "Corolla", Category.SEDAN, CarClass.ECONOMY, "ABC-1234", 2025, "Black", BigDecimal.valueOf(95.00), true);
        Car updatedCar = new Car(carId, "Toyota", "Corolla", Category.SEDAN, CarClass.ECONOMY, "ABC-1234", 2025, "Black", BigDecimal.valueOf(95.00), true);

        Mockito.when(carGateway.updateCar(carId, carToUpdate)).thenReturn(updatedCar);

        Car actualCar = updateCarUseCase.execute(carId, carToUpdate);

        assertNotNull(actualCar);
        assertEquals(updatedCar, actualCar);
        Mockito.verify(carGateway, Mockito.times(1)).updateCar(carId, carToUpdate);
    }
}
