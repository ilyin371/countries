package com.countries.adapters.restcountries;

import com.countries.adapters.restcountries.dto.CountryRecord;
import com.countries.adapters.restcountries.dto.CurrencyRecord;
import com.countries.entities.measure.QuantityUtils;
import com.countries.entities.measure.Units;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RestCountryMapperTest {

    private RestCountryMapper mapper = Mappers.getMapper(RestCountryMapper.class);

    @Test
    void map() {
        val source = new CountryRecord(
                "Portugal",
                "Lisbon",
                10374822,
                92090.0,
                List.of(new CurrencyRecord("EUR", "Euro", "E")));

        val result = mapper.map(source);

        assertThat(result.getName()).isEqualTo("Portugal");
        assertThat(result.getCapital()).isEqualTo("Lisbon");
        assertThat(result.getPopulation()).isEqualTo(10374822);

        assertThat(result.getPopulationDensity().getValue().intValue()).isEqualTo(112);
        assertThat(result.getPopulationDensity().getUnit().toString()).isEqualTo("people/km²");

        assertThat(result.getArea().getValue()).isEqualTo(92090);
        assertThat(result.getArea().getUnit()).isEqualTo(Units.SQUARE_KILOMETER);

        val currency = result.getCurrencies().stream().findFirst();

        assertThat(currency).hasValueSatisfying(ccy -> {
            assertThat(ccy.getCode()).isEqualTo("EUR");
            assertThat(ccy.getName()).isEqualTo("Euro");
            assertThat(ccy.getSymbol()).isEqualTo("€");
        });
    }

    @Test
    void mapNullArea() {
        val source = new CountryRecord(
                "French Guiana",
                "Cayenne",
                254541,
                null,
                List.of(new CurrencyRecord("EUR", "Euro", "E")));

        val result = mapper.map(source);

        assertThat(result.getName()).isEqualTo("French Guiana");
        assertThat(result.getPopulation()).isEqualTo(254541);
        assertThat(result.getArea()).matches(QuantityUtils::isEmpty);
        assertThat(result.getPopulationDensity()).matches(QuantityUtils::isEmpty);
    }

    @Test
    void mapManxPoundByName() {
        val currency = mapper.mapCurrency(new CurrencyRecord("IMP[G]", "Manx pound", "£"));

        assertThat(currency.getCode()).isEqualTo("IMP");
        assertThat(currency.getName()).isEqualTo("Manx pound");
        assertThat(currency.getSymbol()).isEqualTo("£");
    }

    @Test
    void mapKronaByName() {
        val currency =
                mapper.mapCurrency(new CurrencyRecord("(none)", "Faroese króna", "kr"));

        assertThat(currency.getCode()).isEqualTo("FOK");
        assertThat(currency.getName()).isEqualTo("Faroese króna");
        assertThat(currency.getSymbol()).isEqualTo("kr");
    }
}
