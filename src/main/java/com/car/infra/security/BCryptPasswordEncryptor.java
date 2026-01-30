package com.car.infra.security;

import com.car.core.security.PasswordEncryptor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncryptor implements PasswordEncryptor {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Override
    public String encrypt(String password) {
        return encoder.encode(password);
    }
}
