package com.car.infra.security;

import com.car.core.security.PasswordEncryptor;

public class BCryptPasswordEncryptor implements PasswordEncryptor {
    private final BCryptPasswordEncryptor encoder = new BCryptPasswordEncryptor();

    @Override
    public String encrypt(String password) {
        return encoder.encrypt(password);
    }
}
