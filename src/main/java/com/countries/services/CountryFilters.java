package com.countries.services;

import com.countries.entities.Country;
import com.countries.entities.currency.Currency;
import com.countries.services.query.Field;
import com.countries.services.query.Filter;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@Component
public class CountryFilters {

    private final Map<Field, PredicateProvider> filtersMap = Map.of(
            Field.NAME, this::nameFilter,
            Field.CURRENCY_CODE, this::currencyCodeFilter
    );

    public Predicate<Country> composeFilters(List<Filter> params) {
        val predicate = params.stream()
                .map(param -> filtersMap.get(param.getField()).get(param.getValue()))
                .reduce(country -> true, Predicate::and);
        return predicate;
    }

    private Predicate<Country> nameFilter(String namePattern) {
        val regex = "(?i)" + namePattern.replaceAll("\\*", ".*");
        return country -> country.getName().matches(regex);
    }

    private Predicate<Country> currencyCodeFilter(String currencyCode) {
        val currency = Currency.of(currencyCode.toUpperCase());
        return country -> country.getCurrencies().contains(currency);
    }

    interface PredicateProvider {
        Predicate<Country> get(String value);
    }
}


