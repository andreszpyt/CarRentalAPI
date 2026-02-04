package com.car.core.security;

public record UserAuthenticated(
        String token,
        String name,
        String role
) {
}
