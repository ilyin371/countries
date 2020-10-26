package com.countries.entities;

import com.countries.entities.currency.Currency;
import com.countries.entities.measure.QuantityUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.val;
import tech.units.indriya.ComparableQuantity;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Area;
import java.util.Optional;
import java.util.Set;

import static com.countries.entities.measure.Units.PEOPLE;
import static com.countries.entities.measure.Units.SQUARE_KILOMETER;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    private static Unit<Area> AREA_UNIT = SQUARE_KILOMETER;

    String name;
    String capital;
    int population;
    Quantity<Area> area;
    Set<Currency> currencies;

    @ToString.Include(name = "populationDensity")
    public ComparableQuantity<PopulationDensity> getPopulationDensity() {

        return QuantityUtils.maybe(this.area)
                .map(area -> {
                    val population = Quantities.getQuantity(this.population, PEOPLE);
                    val density = population.divide(this.area.to(AREA_UNIT))
                            .asType(PopulationDensity.class);

                    return density;
                })
                .orElse(QuantityUtils.emptyQuantity());
    }
}
