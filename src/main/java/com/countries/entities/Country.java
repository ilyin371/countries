package com.countries.entities;

import com.countries.entities.currency.Currency;
import lombok.ToString;
import lombok.Value;
import lombok.val;
import tech.units.indriya.ComparableQuantity;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import javax.measure.quantity.Area;
import java.util.Set;

import static com.countries.entities.Units.PEOPLE;
import static com.countries.entities.Units.SQUARE_KILOMETER;

@Value
public class Country {

    String name;
    String capital;
    int population;
    Quantity<Area> area;
    Set<Currency> currencies;

    @ToString.Include(name = "populationDensity")
    public ComparableQuantity<PopulationDensity> getPopulationDensity() {

        val population = Quantities.getQuantity(this.population, PEOPLE);
        val density = population.divide(this.area.to(SQUARE_KILOMETER))
                .asType(PopulationDensity.class);

        return density;
    }
}
