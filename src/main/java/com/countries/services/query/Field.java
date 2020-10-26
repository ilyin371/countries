package com.countries.services.query;

public enum Field {
    NAME("name"),
    CAPITAL("capital"),
    AREA("area"),
    POPULATION("population"),
    POPULATION_DENSITY("populationDensity"),
    CURRENCY_CODE("currencyCode"),
    CURRENCY_NAME("currencyName");

    private final String name;

    Field(String name) {
        this.name = name;
    }
}
