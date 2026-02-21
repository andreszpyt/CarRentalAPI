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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class FindCarsUseCaseImplTest {
    @Mock
    private CarGateway carGateway;

    @InjectMocks
    private FindCarsUseCaseImpl findCarsUseCase;

    @Test
    void shouldReturnListOfCarsWhenCarsExist() {
        Car car1 = new Car(1L, "Toyota", "Corolla", Category.SEDAN, CarClass.ECONOMY, "ABC-1234", 2024, "Silver", BigDecimal.valueOf(89.90), true);
        Car car2 = new Car(2L, "Honda", "Civic", Category.SEDAN, CarClass.COMPACT, "DEF-5678", 2024, "Black", BigDecimal.valueOf(99.90), true);
        List<Car> expectedCars = List.of(car1, car2);

        Mockito.when(carGateway.findCars()).thenReturn(expectedCars);

        List<Car> actualCars = findCarsUseCase.execute();

        assertNotNull(actualCars);
        assertEquals(expectedCars.size(), actualCars.size());
        assertEquals(expectedCars.get(0), actualCars.get(0));
        assertEquals(expectedCars.get(1), actualCars.get(1));
        Mockito.verify(carGateway, Mockito.times(1)).findCars();
    }

    @Test
    void shouldReturnEmptyListWhenNoCarsExist() {
        Mockito.when(carGateway.findCars()).thenReturn(List.of());

        List<Car> actualCars = findCarsUseCase.execute();

        assertNotNull(actualCars);
        assertEquals(0, actualCars.size());
        Mockito.verify(carGateway, Mockito.times(1)).findCars();
    }
}
