package com.car.infra.config;

import com.car.core.gateway.CarGateway;
import com.car.core.gateway.CustomerGateway;
import com.car.core.gateway.RentalGateway;
import com.car.core.security.PasswordEncryptor;
import com.car.core.usecases.car.commands.*;
import com.car.core.usecases.car.queries.*;
import com.car.core.usecases.customer.commands.RegisterCustomerUseCase;
import com.car.core.usecases.customer.commands.RegisterCustomerUseCaseImpl;
import com.car.core.usecases.rental.RentCarUseCase;
import com.car.core.usecases.rental.RentCarUseCaseImpl;
import com.car.infra.security.BCryptPasswordEncryptor;
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

    @Bean
    public FindByIdUseCase findByIdUseCase(CarGateway carGateway){
        return new FindByIdUseCaseImpl(carGateway);
    }

    @Bean
    public DeleteCarUseCase deleteCarUseCase(CarGateway carGateway){
        return new DeleteCarUseCaseImpl(carGateway);
    }

    @Bean
    public PasswordEncryptor passwordEncoder() {
        return new BCryptPasswordEncryptor();
    }

    @Bean
    public RegisterCustomerUseCase registerCustomerUseCase(CustomerGateway customerGateway, PasswordEncryptor passwordEncryptor){
        return new RegisterCustomerUseCaseImpl(customerGateway, passwordEncryptor);
    }

    @Bean
    public RentCarUseCase rentCarUseCase(RentalGateway rentalGateway, CarGateway carGateway, CustomerGateway customerGateway){
        return new RentCarUseCaseImpl(rentalGateway, carGateway, customerGateway);
    }
}
