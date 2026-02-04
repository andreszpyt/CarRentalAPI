package com.car.core.security;

public record LoginRequest(
        String email,
        String password
) {
}
