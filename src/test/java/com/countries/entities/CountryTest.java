package com.countries.entities;

import lombok.val;
import org.junit.jupiter.api.Test;
import tech.units.indriya.quantity.Quantities;

import java.util.Set;

import static com.countries.entities.measure.Units.SQUARE_KILOMETER;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class CountryTest {

    @Test
    void getPopulationDensity() {
        val country = new Country(
                "Country",
                "Capital",
                500,
                Quantities.getQuantity(10, SQUARE_KILOMETER),
                Set.of());

        val density = country.getPopulationDensity();

        assertThat(density.getValue()).isEqualTo(50);
        assertThat(density.getUnit().toString()).isEqualTo("people/km²");
    }
}
