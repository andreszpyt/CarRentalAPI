package com.car.infra.config;

import com.car.core.gateway.CarGateway;
import com.car.core.gateway.CostumerGateway;
import com.car.core.usecases.car.commands.*;
import com.car.core.usecases.car.queries.*;
import com.car.core.usecases.costumer.commands.RegisterCostumerUseCase;
import com.car.core.usecases.costumer.commands.RegisterCostumerUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    @Bean
    public FindByIdUseCase findByIdUseCase(CarGateway carGateway){
        return new FindByIdUseCaseImpl(carGateway);
    }

    @Bean
    public DeleteCarUseCase deleteCarUseCase(CarGateway carGateway){
        return new DeleteCarUseCaseImpl(carGateway);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RegisterCostumerUseCase registerCostumerUseCase(CostumerGateway costumerGateway, BCryptPasswordEncoder encoder){
        return new RegisterCostumerUseCaseImpl(costumerGateway, encoder);
    }
}
