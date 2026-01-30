package com.car.core.entities.vo;

import com.car.core.entities.exceptions.InvalidCpfException;

public record Cpf(String value) {

    public Cpf {
        if (value == null) {
            throw new InvalidCpfException("null");
        }

        value = value.replaceAll("\\D", "");

        if (value.length() != 11 || isKnownInvalidCpf(value) || !isValidCpf(value)) {
            throw new InvalidCpfException(value);
        }
    }

    private static boolean isValidCpf(String cpf) {
        try {
            int d1 = 0, d2 = 0;
            int digit1, digit2, rest;
            int nCount;

            for (nCount = 1; nCount < cpf.length() - 1; nCount++) {
                int digit = Integer.parseInt(cpf.substring(nCount - 1, nCount));
                d1 += (11 - nCount) * digit;
                d2 += (12 - nCount) * digit;
            }

            rest = (d1 % 11);
            digit1 = (rest < 2) ? 0 : 11 - rest;

            d2 += 2 * digit1;
            rest = (d2 % 11);
            digit2 = (rest < 2) ? 0 : 11 - rest;

            String predictedDigit = "" + digit1 + "" + digit2;
            return cpf.substring(cpf.length() - 2).equals(predictedDigit);
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isKnownInvalidCpf(String cpf) {
        return cpf.matches("(\\d)\\1{10}");
    }

    public String getFormatted() {
        return value.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }
}