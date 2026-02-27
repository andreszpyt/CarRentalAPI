package com.car.core.usecases.customer.auth;

import com.car.core.entities.Customer;
import com.car.core.gateway.CustomerGateway;
import com.car.core.security.LoginRequest;
import com.car.core.security.PasswordEncryptor;
import com.car.core.security.TokenService;
import com.car.core.security.UserAuthenticated;
import com.car.core.usecases.exception.UnauthorizedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticateUserUseCaseImplTest {
    @Mock
    private CustomerGateway customerGateway;
    @Mock
    private PasswordEncryptor passwordEncryptor;
    @Mock
    private TokenService tokenService;

    @InjectMocks
    private AuthenticateUserUseCaseImpl authenticateUserUseCase;

    @Test
    void shouldAuthenticateUserSuccessfully(){
        Customer customer = mock(Customer.class);

        when(customer.password()).thenReturn("hashed_password_from_db");
        when(customer.name()).thenReturn("John Doe");
        when(customer.role()).thenReturn("USER");

        when(customerGateway.findByEmail("john@example.com")).thenReturn(Optional.of(customer));
        when(passwordEncryptor.checkPassword("mySecret123", "hashed_password_from_db")).thenReturn(true);
        when(tokenService.generateToken(customer)).thenReturn("jwt.token.here");

        LoginRequest loginRequest = new LoginRequest("john@example.com", "mySecret123");

        UserAuthenticated result = authenticateUserUseCase.execute(loginRequest);

        assertNotNull(result);
        assertEquals("jwt.token.here", result.token());
        assertEquals("John Doe", result.name());
        assertEquals("USER", result.role());

        verify(customerGateway, times(1)).findByEmail("john@example.com");
        verify(passwordEncryptor, times(1)).checkPassword("mySecret123", "hashed_password_from_db");
        verify(tokenService, times(1)).generateToken(customer);
    }

    @Test
    void shouldThrowUnauthorizedExceptionWhenEmailNotFound(){
        when(customerGateway.findByEmail(anyString())).thenReturn(Optional.empty());
        LoginRequest loginRequest = new LoginRequest("john@example.com", "mySecret123");
        UnauthorizedException exception = assertThrows(UnauthorizedException.class, () -> authenticateUserUseCase.execute(loginRequest));
        assertEquals("Invalid credentials", exception.getMessage());
        verify(customerGateway, times(1)).findByEmail(loginRequest.email());
    }

    @Test
    void shouldThrowUnauthorizedExceptionWhenPasswordIsInvalid() {
        Customer customer = mock(Customer.class);
        when(customer.password()).thenReturn("hashed_password_from_db");
        when(customerGateway.findByEmail(anyString())).thenReturn(Optional.of(customer));
        when(passwordEncryptor.checkPassword(anyString(), anyString())).thenReturn(false);

        LoginRequest loginRequest = new LoginRequest("john@example.com", "mySecret123");
        UnauthorizedException exception = assertThrows(UnauthorizedException.class, () -> authenticateUserUseCase.execute(loginRequest));
        assertEquals("Invalid credentials", exception.getMessage());
        verify(customerGateway, times(1)).findByEmail(loginRequest.email());
    }
}
