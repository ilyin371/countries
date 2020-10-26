package com.countries.services;

import com.countries.entities.Country;
import com.countries.entities.Units;
import lombok.val;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import tech.units.indriya.quantity.Quantities;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class CountriesServiceTest {

    private static Country country(String name, int population, int areaSqKm) {
        return new Country(name, "Capital", population, Quantities.getQuantity(areaSqKm, Units.SQUARE_KILOMETER), Set.of());
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
                .collect(Collectors.toList())
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
                .collect(Collectors.toList())
                .block();

        assertThat(top).extracting("name").containsExactly(
                "Lithuania",
                "Latvia"
        );
    }
}
