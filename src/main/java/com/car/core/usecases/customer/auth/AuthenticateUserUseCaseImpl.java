package com.car.core.usecases.customer.auth;

import com.car.core.entities.Customer;
import com.car.core.gateway.CustomerGateway;
import com.car.core.security.LoginRequest;
import com.car.core.security.PasswordEncryptor;
import com.car.core.security.TokenService;
import com.car.core.security.UserAuthenticated;
import com.car.core.usecases.exception.UnauthorizedException;


public class AuthenticateUserUseCaseImpl implements  AuthenticateUserUseCase {
    private final CustomerGateway customerGateway;
    private final PasswordEncryptor passwordEncryptor;
    private final TokenService tokenService;

    public AuthenticateUserUseCaseImpl(CustomerGateway customerGateway, PasswordEncryptor passwordEncryptor, TokenService tokenService) {
        this.customerGateway = customerGateway;
        this.passwordEncryptor = passwordEncryptor;
        this.tokenService = tokenService;
    }

    @Override
    public UserAuthenticated execute(LoginRequest loginRequest) {
        Customer customer = customerGateway.findByEmail(loginRequest.email())
                .orElseThrow(() -> new UnauthorizedException("Invalid credentials"));

        boolean validPassword = passwordEncryptor.checkPassword(loginRequest.password(), customer.password());

        if (!validPassword) {
            throw new UnauthorizedException("Invalid credentials");
        }

        return new UserAuthenticated(
                tokenService.generateToken(customer),
                customer.name(),
                customer.role()
        );
    }

}
