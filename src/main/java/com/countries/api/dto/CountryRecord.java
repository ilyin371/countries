package com.countries.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Set;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryRecord {
    String name;
    String capital;
    int population;

    @Nullable
    QuantityRecord<Double> area;
    @Nullable
    QuantityRecord<Integer> populationDensity;

    private Set<CurrencyRecord> currencies;
}
