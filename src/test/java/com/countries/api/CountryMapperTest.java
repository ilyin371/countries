package com.countries.api;

import com.countries.entities.Country;
import com.countries.entities.currency.Currency;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Set;

import static com.countries.entities.measure.QuantityUtils.area;

class CountryMapperTest {

    private final CountryMapper mapper = Mappers.getMapper(CountryMapper.class);

    @Test
    void map() {
        val source = new Country("Portugal", "Lisbon", 10374822, area(92090), Set.of(Currency.of("EUR")));

        val result = mapper.map(source);

        // TODO: test
        System.out.println(result);
    }
}
