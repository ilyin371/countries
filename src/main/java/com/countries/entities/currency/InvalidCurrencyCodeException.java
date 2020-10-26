package com.countries.entities.currency;

public class InvalidCurrencyCodeException extends IllegalArgumentException {
    public InvalidCurrencyCodeException(String code) {
        super(code);
    }
}
