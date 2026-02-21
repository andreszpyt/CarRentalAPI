package com.car.core.usecases.car.queries;


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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FindByPlateUseCaseImplTest {
    @Mock
    private CarGateway carGateway;

    @InjectMocks
    private FindByPlateUseCaseImpl findByPlateUseCase;

    @Test
    void shouldReturnCarWhenPlateExists(){
        String plate = "ABC-1234";
        Car expectedCar = new Car(1L, "Toyota", "Corolla", Category.SEDAN, CarClass.ECONOMY, "ABC-1234", 2024, "Silver", BigDecimal.valueOf(89.90), true);
        Mockito.when(carGateway.findCarByPlate(plate)).thenReturn(java.util.Optional.of(expectedCar));
        Optional<Car> actualCar = findByPlateUseCase.execute(plate);
        assertNotNull(actualCar);
        assertEquals(expectedCar, actualCar.get());
        Mockito.verify(carGateway, Mockito.times(1)).findCarByPlate(plate);
    }

    @Test
    void shouldReturnEmptyOptionalWhenPlateDoesNotExist() {
        String plate = "XYZ-9999";
        Mockito.when(carGateway.findCarByPlate(plate)).thenReturn(Optional.empty());
        Optional<Car> actualCar = findByPlateUseCase.execute(plate);
        assertTrue(actualCar.isEmpty());
        Mockito.verify(carGateway, Mockito.times(1)).findCarByPlate(plate);
    }
}
