package com.countries.services.query;

import lombok.Value;

@Value(staticConstructor = "of")
public class Filter {
    private Field field;
    private String value;
}
