package com.countries.api.query;

public enum SortableField {
    NAME("name"),
    CAPITAL("capital"),
    AREA("area"),
    POPULATION("population"),
    POPULATION_DENSITY("populationDensity"),
    CURRENCY_CODE("currencyCode"),
    CURRENCY_NAME("currencyName");

    private final String name;

    SortableField(String name) {
        this.name = name;
    }

    public static SortableField of(String name) {
        for (SortableField v : values()) {
            if (v.name.equals(name)) {
                return v;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
