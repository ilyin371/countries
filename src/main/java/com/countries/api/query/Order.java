package com.countries.api.query;

public enum Order {
    ASC("asc"),
    DESC("desc");

    private final String name;

    Order(String name) {
        this.name = name;
    }

    public static Order of(String name) {
        for (Order v : values()) {
            if (v.name.equals(name)) {
                return v;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
