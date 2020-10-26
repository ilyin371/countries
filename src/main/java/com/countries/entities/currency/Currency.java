package com.countries.entities.currency;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.val;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Currency {
    private static Map<String, Currency> instances = new HashMap<>();

    static {
        val currenciesWithUnofficialCode = List.of(
                new Currency("IMP", "Manx pound", "£"),
                new Currency("FOK", "Faroese króna", "kr")
        ).stream().collect(Collectors.toMap(Currency::getCode, currency -> currency));
        instances.putAll(currenciesWithUnofficialCode);
    }

    String code;
    String name;
    String symbol;

    private Currency(java.util.Currency currency) {
        this(currency.getCurrencyCode(), currency.getDisplayName(), currency.getSymbol());
    }

    public static Currency of(String code) {
        if (instances.containsKey(code)) {
            return instances.get(code);
        }
        val currency = new Currency(getIsoCurrency(code));
        instances.put(currency.code, currency);
        return currency;
    }

    public static boolean isValidCode(String code) {
        return instances.containsKey(code);
    }

    private static java.util.Currency getIsoCurrency(String code) {
        try {
            return java.util.Currency.getInstance(code);
        } catch (IllegalArgumentException e) {
            throw new InvalidCurrencyCodeException(code);
        }
    }
}
