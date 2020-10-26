package com.countries;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CountriesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountriesApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("basicScheme",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
                .info(new Info().title("Countries API"));
    }

    @Bean
    public GroupedOpenApi countriesOpenApi() {
        String[] paths = {"/countries/**"};
        return GroupedOpenApi.builder().group("countries").pathsToMatch(paths)
                .build();
    }

}
