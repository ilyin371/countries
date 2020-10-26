package com.countries.services;

import com.countries.entities.Country;
import com.countries.entities.RegionalBloc;
import com.countries.services.query.Field;
import com.countries.services.query.Query;
import com.countries.services.query.Settings;
import com.countries.services.query.SortOrder;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountriesService {

    private final CountriesProvider provider;
    private final CountryFilters filters;
    private final CountrySort sort;

    private final static RegionalBloc region = RegionalBloc.EU;

    public Flux<Country> getTopByPopulationDensity(Optional<Integer> limit) {
        val query = new Query()
                .withSortBy(Field.POPULATION_DENSITY)
                .withOrder(SortOrder.DESC)
                .withLimit(limit.orElse(Settings.DEFAULT_LIMIT));

        return getCountries(query);
    }

    public Flux<Country> findByCurrencyCode(String currencyCode) {
        val query = Query.withFilter(Field.CURRENCY_CODE, currencyCode);
        return getCountries(query);
    }

    public Flux<Country> findByName(String namePattern) {
        val query = Query.withFilter(Field.NAME, namePattern);
        return getCountries(query);
    }

    public Flux<Country> getCountries(Query query) {

        val predicate = filters.composeFilters(query.getFilters());
        val comparator = sort.getComparator(query.getSortBy(), query.getOrder());

        return provider.getCountries(region)
                .filter(predicate)
                .sort(comparator)
                .take(query.getLimit());
    }
}


