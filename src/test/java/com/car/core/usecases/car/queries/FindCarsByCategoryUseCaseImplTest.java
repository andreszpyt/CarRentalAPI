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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindCarsByCategoryUseCaseImplTest {
    @Mock
    private CarGateway carGateway;

    @InjectMocks
    private FindCarsByCategoryUseCaseImpl findCarsByCategoryUseCase;

    @Test
    void shouldReturnCarsWhenCategoryHasCars(){
        String category = "SEDAN";
        Car car1 = new Car(1L, "Toyota", "Corolla", Category.SEDAN, CarClass.ECONOMY, "ABC-1234", 2024, "Silver", BigDecimal.valueOf(89.90), true);
        Car car2 = new Car(2L, "Honda", "Civic", Category.SEDAN, CarClass.COMPACT, "DEF-5678", 2024, "Black", BigDecimal.valueOf(99.90), true);

        when(carGateway.findCarByCategory(category)).thenReturn(List.of(car1, car2));

        List<Car> actualCars = findCarsByCategoryUseCase.execute(category);

        assertNotNull(actualCars);
        assertEquals(2, actualCars.size());
        assertEquals(car1, actualCars.get(0));
        assertEquals(car2, actualCars.get(1));
        Mockito.verify(carGateway, Mockito.times(1)).findCarByCategory(category);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenCategoryHasNoCars(){
        String category = "SUV";

        when(carGateway.findCarByCategory(category)).thenReturn(List.of());

        try {
            findCarsByCategoryUseCase.execute(category);
        } catch (Exception e) {
            assertEquals("Car not found", e.getMessage());
        }

        Mockito.verify(carGateway, Mockito.times(1)).findCarByCategory(category);
    }
}
