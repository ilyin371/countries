package com.countries.adapters.restcountries;

import com.countries.adapters.restcountries.dto.CountryRecord;
import com.countries.entities.Country;
import com.countries.entities.RegionalBloc;
import com.countries.services.CountriesProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class RestCountriesClient implements CountriesProvider {

    private final RestCountryMapper mapper;

    // TODO: extract service URL to config
    private final WebClient client = WebClient.create("https://restcountries.eu/rest/v2/regionalbloc/eu?fields=name;capital;currencies;population;area");

    public RestCountriesClient(RestCountryMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Flux<Country> getCountries(RegionalBloc region) {
        return this.client.get()
                .retrieve()
                .bodyToFlux(CountryRecord.class)
                .map(mapper::map);
    }
}
