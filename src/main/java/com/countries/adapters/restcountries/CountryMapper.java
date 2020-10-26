package com.countries.adapters.restcountries;

import com.countries.entities.Country;
import com.countries.entities.measure.QuantityUtils;
import com.countries.entities.measure.Units;
import com.countries.entities.currency.Currency;
import org.mapstruct.Mapper;
import org.springframework.lang.Nullable;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import javax.measure.quantity.Area;
import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class CountryMapper {

    abstract Country map(CountryRecord source);

    Quantity<Area> mapArea(@Nullable Integer areaOrNull) {
        return Optional.ofNullable(areaOrNull)
                .map(area -> Quantities.getQuantity(area, Units.SQUARE_KILOMETER))
                .orElse(QuantityUtils.emptyQuantity());
    }

    Currency mapCurrency(CurrencyRecord currency) {
        if (Currency.isValidCode(currency.getCode())) {
            return Currency.of(currency.getCode());
        }
        return Currency.ofName(currency.getName());
    }
}
