package com.countries.adapters.restcountries.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryRecord {
    private String name;
    private String capital;
    private int population;

    @Nullable
    private Double area;
    private List<CurrencyRecord> currencies;
}
