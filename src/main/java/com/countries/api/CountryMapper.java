package com.countries.api;

import com.countries.api.dto.CountryRecord;
import com.countries.api.dto.CurrencyRecord;
import com.countries.api.dto.QuantityRecord;
import com.countries.entities.Country;
import com.countries.entities.PopulationDensity;
import com.countries.entities.currency.Currency;
import com.countries.entities.measure.QuantityUtils;
import org.mapstruct.Mapper;
import org.springframework.lang.Nullable;

import javax.measure.Quantity;
import javax.measure.quantity.Area;
import java.util.function.Function;

@Mapper(componentModel = "spring")
public abstract class CountryMapper {

    abstract CountryRecord map(Country source);

    abstract CurrencyRecord mapCurrency(Currency currency);

    @Nullable
    QuantityRecord<Double> mapArea(Quantity<Area> source) {
        return mapQuantity(source, Number::doubleValue);
    }

    @Nullable
    QuantityRecord<Integer> mapDensity(Quantity<PopulationDensity> source) {
        return mapQuantity(source, Number::intValue);
    }

    private <T extends Number> QuantityRecord<T> mapQuantity(Quantity<?> source, Function<Number, T> valueGetter) {
        return QuantityUtils.maybe(source)
                .map(quantity -> new QuantityRecord<T>(valueGetter.apply(quantity.getValue()), quantity.getUnit().toString()))
                .orElse(null);
    }
}
