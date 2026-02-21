package com.car.infra.presentation;

import com.car.core.entities.Car;
import com.car.core.entities.enums.CarClass;
import com.car.core.entities.enums.Category;
import com.car.core.security.TokenService;
import com.car.core.usecases.car.commands.DeleteCarUseCase;
import com.car.core.usecases.car.commands.RegisterCarUseCase;
import com.car.core.usecases.car.commands.UpdateCarUseCase;
import com.car.core.usecases.car.queries.FindByIdUseCase;
import com.car.core.usecases.car.queries.FindByPlateUseCase;
import com.car.core.usecases.car.queries.FindCarsByCategoryUseCase;
import com.car.core.usecases.car.queries.FindCarsUseCase;
import com.car.infra.dtos.request.CarRequest;
import com.car.infra.dtos.response.CarResponse;
import com.car.infra.mapper.CarMapper;
import com.car.infra.persistence.CustomerRepository;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CarMapper carMapper;

    @MockitoBean
    private RegisterCarUseCase registerCarUseCase;

    @MockitoBean
    private TokenService tokenService;

    @MockitoBean
    private FindCarsUseCase findCarsUseCase;

    @MockitoBean
    private FindByPlateUseCase findByPlateUseCase;

    @MockitoBean
    private UpdateCarUseCase updateCarUseCase;

    @MockitoBean
    private CustomerRepository customerRepository;

    @MockitoBean
    private FindByIdUseCase findByIdUseCase;

    @MockitoBean
    private DeleteCarUseCase deleteCarUseCase;

    @MockitoBean
    private FindCarsByCategoryUseCase findCarsByCategoryUseCase;

    @Test
    void shouldCreateCarSuccessfully() throws Exception {
        CarRequest request = new CarRequest("Toyota", "Corolla", Category.SEDAN, CarClass.ECONOMY, "ABC-1234", 2024, "Silver", BigDecimal.valueOf(89.90), true);
        Car domainCar = new Car(null, "Toyota", "Corolla", Category.SEDAN, CarClass.ECONOMY, "ABC-1234", 2024, "Silver", BigDecimal.valueOf(89.90), true);
        Car savedCar = new Car(1L, "Toyota", "Corolla", Category.SEDAN, CarClass.ECONOMY, "ABC-1234", 2024, "Silver", BigDecimal.valueOf(89.90), true);
        CarResponse response = new CarResponse(1L, "Toyota", "Corolla", Category.SEDAN, CarClass.ECONOMY, "ABC-1234", 2024, "Silver", BigDecimal.valueOf(89.90), true);

        Mockito.when(carMapper.toDomain(any(CarRequest.class))).thenReturn(domainCar);
        Mockito.when(registerCarUseCase.execute(domainCar)).thenReturn(savedCar);
        Mockito.when(carMapper.toResponse(savedCar)).thenReturn(response);

        mockMvc.perform(post("/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.brand").value("Toyota"))
                .andExpect(jsonPath("$.carId").value(1));
    }
}