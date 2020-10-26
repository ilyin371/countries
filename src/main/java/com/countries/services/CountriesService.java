package com.countries.services;

import com.countries.entities.Country;
import com.countries.entities.RegionalBloc;
import lombok.val;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Comparator;
import java.util.Currency;
import java.util.Optional;

@Service
public class CountriesService {

    private final static int DEFAULT_LIMIT = 10;

    private final CountriesProvider provider;
    private final RegionalBloc region = RegionalBloc.EU;

    public CountriesService(CountriesProvider provider) {
        this.provider = provider;
    }

    Flux<Country> getTopByPopulationDensity(Optional<Integer> limit) {
        return getCountries()
                .sort(Comparator.comparing(Country::getPopulationDensity).reversed())
                .take(limit.orElse(DEFAULT_LIMIT));
    }

    Flux<Country> findByCurrency(Currency currency) {
        return getCountries()
                .filter(country -> country.getCurrencies().contains(currency));
    }

    Flux<Country> findByName(String namePattern) {
        val regex = "(?i)" + namePattern.replaceAll("\\*", ".*");
        return getCountries()
                .filter(country -> country.getName().matches(regex));
    }

    private Flux<Country> getCountries() {
        return provider.getCountries(region);
    }
}
