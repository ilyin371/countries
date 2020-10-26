package com.countries.adapters.restcountries;

import com.countries.adapters.restcountries.dto.CountryRecord;
import com.countries.adapters.restcountries.dto.CurrencyRecord;
import com.countries.entities.Country;
import com.countries.entities.currency.Currency;
import com.countries.entities.measure.QuantityUtils;
import org.mapstruct.Mapper;
import org.springframework.lang.Nullable;
import tech.units.indriya.ComparableQuantity;

import javax.measure.quantity.Area;

@Mapper(componentModel = "spring")
public abstract class RestCountryMapper {

    abstract Country map(CountryRecord source);

    ComparableQuantity<Area> mapArea(@Nullable Double area) {
        return QuantityUtils.area(area);
    }

    Currency mapCurrency(CurrencyRecord currency) {
        if (Currency.isValidCode(currency.getCode())) {
            return Currency.of(currency.getCode());
        }
        return Currency.ofName(currency.getName());
    }
}
