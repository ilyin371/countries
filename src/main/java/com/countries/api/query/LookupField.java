package com.countries.api.query;

public enum LookupField {
    NAME("name"),
    CAPITAL("capital"),
    CURRENCY_CODE("currencyCode");

    private final String name;

    LookupField(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
