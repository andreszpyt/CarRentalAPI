package com.car.infra.security;

import com.car.core.security.PasswordEncryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

public class BCryptPasswordEncryptor implements PasswordEncryptor {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Override
    public String encrypt(String password) {
        return encoder.encode(password);
    }

    @Override
    public boolean checkPassword(String password, String encryptedPassword) {
        return encoder.matches(password, encryptedPassword);
    }
}
