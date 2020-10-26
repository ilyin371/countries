package com.countries.api;

import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class CountriesRouter {

    @Bean
    @RouterOperations({@RouterOperation(path = "/countries", beanClass = CountriesRestApi.class, beanMethod = "find"),
            @RouterOperation(path = "/countries/{name}", beanClass = CountriesRestApi.class, beanMethod = "findByName")})
    public RouterFunction<ServerResponse> route(CountriesHandler handler) {
        return RouterFunctions.route(GET("/countries"), handler::getCountries)
                .andRoute(GET("/countries/{name}"), handler::findByName);
    }
}
