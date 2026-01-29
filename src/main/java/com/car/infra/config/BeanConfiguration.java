package com.car.infra.config;

import com.car.core.gateway.CarGateway;
import com.car.core.usecases.car.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public RegisterCarUseCaseImpl registerCarUseCase(CarGateway carGateway){
        return new RegisterCarUseCaseImpl(carGateway);
    }

    @Bean
    public FindCarsUseCaseImpl findCarsUseCase(CarGateway carGateway){
        return new FindCarsUseCaseImpl(carGateway);
    }

    @Bean
    public FindByPlateUseCase findByPlateUseCase(CarGateway carGateway){
        return new FindByPlateUseCaseImpl(carGateway);
    }

    @Bean
    public UpdateCarUseCase updateCarUseCase(CarGateway carGateway){
        return new UpdateCarUseCaseImpl(carGateway);
    }
}
