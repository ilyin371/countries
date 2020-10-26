package com.countries.entities.currency;

public class InvalidCurrencyException extends IllegalArgumentException {
    public InvalidCurrencyException(String code) {
        super(code);
    }
}
