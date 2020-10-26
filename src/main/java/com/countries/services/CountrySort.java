package com.countries.services;

import com.countries.entities.Country;
import com.countries.services.query.Field;
import com.countries.services.query.SortOrder;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Map;

@Component
public class CountrySort {

    private final Map<Field, Comparator<Country>> comparators = Map.of(
            Field.NAME, Comparator.comparing(Country::getName),
            Field.POPULATION, Comparator.comparing(Country::getPopulation),
            Field.POPULATION_DENSITY, Comparator.comparing(Country::getPopulationDensity),
            Field.AREA, Comparator.comparing(Country::getArea)
    );

    public Comparator<Country> getComparator(Field field, SortOrder order) {
        val comparator = this.comparators.get(field);
        return order == SortOrder.DESC ? comparator.reversed() : comparator;
    }
}
