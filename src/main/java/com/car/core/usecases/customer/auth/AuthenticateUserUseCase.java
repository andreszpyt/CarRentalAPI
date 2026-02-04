package com.car.core.usecases.customer.auth;

import com.car.core.security.LoginRequest;
import com.car.core.security.UserAuthenticated;

public interface AuthenticateUserUseCase {
     UserAuthenticated execute(LoginRequest loginRequest);
}
