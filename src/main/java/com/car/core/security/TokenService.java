package com.car.core.security;

import com.car.core.entities.Customer;

public interface TokenService {
    String generateToken(Customer customer);
    String validateToken(String token);
}
