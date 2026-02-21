package com.car.infra.config;

import com.car.core.gateway.CarGateway;
import com.car.core.gateway.CustomerGateway;
import com.car.core.gateway.RentalGateway;
import com.car.core.security.PasswordEncryptor;
import com.car.core.security.TokenService;
import com.car.core.usecases.car.commands.*;
import com.car.core.usecases.car.queries.*;
import com.car.core.usecases.customer.auth.AuthenticateUserUseCase;
import com.car.core.usecases.customer.auth.AuthenticateUserUseCaseImpl;
import com.car.core.usecases.customer.commands.RegisterCustomerUseCase;
import com.car.core.usecases.customer.commands.RegisterCustomerUseCaseImpl;
import com.car.core.usecases.rental.commands.RentCarUseCase;
import com.car.core.usecases.rental.commands.RentCarUseCaseImpl;
import com.car.core.usecases.rental.commands.ReturnRentUseCase;
import com.car.core.usecases.rental.commands.ReturnRentUseCaseImpl;
import com.car.core.usecases.rental.queries.FindRentByCustomerUseCase;
import com.car.core.usecases.rental.queries.FindRentByCustomerUseCaseImpl;
import com.car.core.usecases.rental.queries.FindRentByIdUseCase;
import com.car.core.usecases.rental.queries.FindRentByIdUseCaseImpl;
import com.car.infra.security.BCryptPasswordEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class BeanConfiguration {

    @Value("${carrental.penalty.amount:400}")
    private BigDecimal penaltyAmount;

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

    @Bean
    public FindRentByIdUseCase findRentByIdUseCase(RentalGateway rentalGateway){
        return new FindRentByIdUseCaseImpl(rentalGateway);
    }

    @Bean
    public ReturnRentUseCase returnRentUseCase(RentalGateway rentalGateway){
        // Passa o valor para o Use Case no momento da criação
        return new ReturnRentUseCaseImpl(rentalGateway, penaltyAmount);
    }

    @Bean
    public FindRentByCustomerUseCase findRentByCustomerUseCase(CustomerGateway customerGateway, RentalGateway rentalGateway){
        return new FindRentByCustomerUseCaseImpl(rentalGateway, customerGateway);
    }

    @Bean
    public FindCarsByCategoryUseCase findCarsByCategoryUseCase(CarGateway carGateway){
        return new FindCarsByCategoryUseCaseImpl(carGateway);
    }

    @Bean
    public AuthenticateUserUseCase authenticateUserUseCase(CustomerGateway customerGateway, PasswordEncryptor passwordEncryptor, TokenService tokenService){
        return new AuthenticateUserUseCaseImpl(customerGateway, passwordEncryptor, tokenService);
    }
}
