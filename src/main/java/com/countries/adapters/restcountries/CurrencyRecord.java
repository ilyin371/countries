package com.countries.adapters.restcountries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRecord {
    private String code;
    private String name;
    private String symbol;
}
