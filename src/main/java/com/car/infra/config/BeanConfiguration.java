package com.car.infra.config;

import com.car.core.gateway.CarGateway;
import com.car.core.usecases.car.RegisterCarUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public RegisterCarUseCaseImpl registerCarUseCase(CarGateway carGateway){
        return new RegisterCarUseCaseImpl(carGateway);
    }
}
