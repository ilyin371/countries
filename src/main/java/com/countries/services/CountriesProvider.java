package com.countries.services;

import com.countries.entities.Country;
import com.countries.entities.RegionalBloc;
import reactor.core.publisher.Flux;

public interface CountriesProvider {

    Flux<Country> getCountries(RegionalBloc region);
}
