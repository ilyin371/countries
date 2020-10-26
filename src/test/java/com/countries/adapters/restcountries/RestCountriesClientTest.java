package com.countries.adapters.restcountries;

import com.countries.entities.RegionalBloc;
import lombok.val;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

class RestCountriesClientTest {

    // TODO: test with mock web client or mock server
    @Test
    @Disabled
    void getCountries() {
        val mapper = Mappers.getMapper(RestCountryMapper.class);
        val service = new RestCountriesClient(mapper);
        val result = service.getCountries(RegionalBloc.EU)
                .collect(Collectors.toList())
                .block();


        System.out.println(result);
    }
}
