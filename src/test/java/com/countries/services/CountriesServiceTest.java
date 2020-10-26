package com.countries.services;

import com.countries.entities.Country;
import com.countries.entities.Units;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import reactor.core.publisher.Flux;
import tech.units.indriya.quantity.Quantities;

import java.util.Arrays;
import java.util.Currency;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class CountriesServiceTest {

    private static Country country(String name, int population, int areaSqKm) {
        return new Country(name, "Capital", population,
                Quantities.getQuantity(areaSqKm, Units.SQUARE_KILOMETER), Set.of());
    }

    private static Country country(String name, Set<String> currencies) {
        return new Country(name, "Capital", 1,
                Quantities.getQuantity(1, Units.SQUARE_KILOMETER),
                currencies.stream()
                        .map(Currency::getInstance)
                        .collect(Collectors.toSet()));
    }

    @Test
    void getTopByPopulationDensity() {

        final CountriesProvider countries = region -> Flux.just(
                country("Latvia", 1961600, 64559),
                country("Lithuania", 2872294, 65300),
                country("Estonia", 1315944, 45227)
        );

        val service = new CountriesService(countries);

        val top = service.getTopByPopulationDensity(Optional.empty())
                .collectList()
                .block();

        assertThat(top).extracting("name").containsExactly(
                "Lithuania",
                "Latvia",
                "Estonia");
    }

    @Test
    void getTopTwoByPopulationDensity() {

        final CountriesProvider countries = region -> Flux.just(
                country("Latvia", 1961600, 64559),
                country("Lithuania", 2872294, 65300),
                country("Estonia", 1315944, 45227)
        );

        val service = new CountriesService(countries);

        val top = service.getTopByPopulationDensity(Optional.of(2))
                .collectList()
                .block();

        assertThat(top).extracting("name").containsExactly(
                "Lithuania",
                "Latvia"
        );
    }

    @ParameterizedTest
    @CsvSource(value = {
            "DKK; Denmark",
            "GIP; Isle of Man",
            "GBP; Isle of Man, United Kingdom",
            "EUR; Latvia, Italy"
    }, delimiter = ';')
    void findByCurrency(String currency, String countries) {

        final CountriesProvider countriesProvider = region -> Flux.just(
                country("Denmark", Set.of("DKK")),
                country("Isle of Man", Set.of("GIP", "GBP")),
                country("United Kingdom", Set.of("GBP")),
                country("Latvia", Set.of("EUR")),
                country("Italy", Set.of("EUR"))
        );

        val service = new CountriesService(countriesProvider);

        val actual = service.findByCurrency(Currency.getInstance(currency))
                .collectList()
                .block();

        val expected = Arrays.stream(countries.split(","))
                .map(String::trim)
                .toArray(String[]::new);

        assertThat(actual).extracting("name").containsOnly(expected);
    }
}
