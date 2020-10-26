package com.countries.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class CountriesRouter {
    @Bean
    public RouterFunction<ServerResponse> route(CountriesHandler handler) {
        return RouterFunctions.route(GET("/countries"), handler::getCountries);
    }
}
