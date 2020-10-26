package com.countries.api;

import com.countries.api.dto.CountryRecord;
import com.countries.services.CountriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CountriesHandler {

    private final CountriesService service;
    private final CountryMapper mapper;
    private final QueryParser parser;

    public Mono<ServerResponse> getCountries(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.getCountries(parser.parse(request))
                                .map(mapper::map),
                        CountryRecord.class);
    }
}
