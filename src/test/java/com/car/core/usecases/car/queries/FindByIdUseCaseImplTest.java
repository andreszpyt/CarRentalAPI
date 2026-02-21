package com.car.core.usecases.car.queries;

import com.car.core.entities.Car;
import com.car.core.entities.enums.CarClass;
import com.car.core.entities.enums.Category;
import com.car.core.gateway.CarGateway;
import com.car.core.usecases.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class FindByIdUseCaseImplTest {
    @Mock
    private CarGateway carGateway;

    @InjectMocks
    private FindByIdUseCaseImpl findByIdUseCase;

    @Test
    void shouldFindCarSuccessfully(){
        Long carId = 1L;
        Car expectedCar = new Car(carId, "Toyota", "Corolla", Category.SEDAN, CarClass.ECONOMY, "ABC-1234", 2024, "Silver", BigDecimal.valueOf(89.90), true);
        Mockito.when(carGateway.findById(carId)).thenReturn(Optional.of(expectedCar));
        Car actualCar = findByIdUseCase.execute(carId);
        assertNotNull(actualCar);
        assertEquals(expectedCar, actualCar);
        Mockito.verify(carGateway, Mockito.times(1)).findById(carId);
        assert actualCar.equals(expectedCar);
    }

    @Test
    void shouldThrowExceptionWhenCarNotFound(){
        Long carId = 1L;
        Mockito.when(carGateway.findById(carId)).thenReturn(Optional.empty());
        NotFoundException exception = org.junit.jupiter.api.Assertions.assertThrows(NotFoundException.class, () -> findByIdUseCase.execute(carId));
        assertEquals("Car not found, with id: " + carId, exception.getMessage());
        Mockito.verify(carGateway, Mockito.times(1)).findById(carId);
    }
}