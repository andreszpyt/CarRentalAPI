package com.car.core.security;


public interface PasswordEncryptor {
    String encrypt(String password);
    boolean checkPassword(String password, String encryptedPassword);
}
