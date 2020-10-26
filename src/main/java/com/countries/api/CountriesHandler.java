package com.countries.api;

import com.countries.api.dto.CountryRecord;
import com.countries.api.query.LookupField;
import com.countries.api.query.QueryParamsParser;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CountriesHandler {

    private final CountriesRestService service;
    private final QueryParamsParser parser;

    public Mono<ServerResponse> findByName(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findByName(request.pathVariable(LookupField.NAME.toString())), CountryRecord.class);
    }

    public Mono<ServerResponse> getCountries(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.get(request), CountryRecord.class);
    }

    private Flux<CountryRecord> get(ServerRequest request) {
        val query = parser.parse(request);
        return service.find(
                query.getCurrencyCode(),
                query.getSortBy(),
                query.getOrder(),
                query.getLimit());
    }
}
