package com.countries.api;

import com.countries.api.dto.CountryRecord;
import com.countries.api.query.Order;
import com.countries.api.query.SortableField;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.lang.Nullable;
import reactor.core.publisher.Flux;

public interface CountriesRestApi {

    String SORT_BY = "sortBy";
    String ORDER = "order";
    String LIMIT = "limit";

    @Operation(description = "Get all the countries by name that corresponds to a given pattern.")
    Flux<CountryRecord> findByName(@Parameter(
            in = ParameterIn.PATH,
            description = "Pattern syntax: * represents any number of any characters, wildcard is case-insensitive.",
            example = "s*N") String namePattern);

    @Operation(description = "Find and sort top N countries by given criteria and field.")
    Flux<CountryRecord> find(
            @Nullable @Parameter(in = ParameterIn.QUERY,
                    description = "ISO 4217 currency code. Unofficial currency codes 'IMP' and 'FOK' are supported.",
                    example = "EUR") String currencyCode,

            @Nullable @Parameter(in = ParameterIn.QUERY,
                    description = "Field name to sort by.",
                    example = "population") SortableField sortBy,

            @Nullable @Parameter(in = ParameterIn.QUERY,
                    description = "Sort order - ascending or descending.",
                    example = "asc") Order order,

            @Nullable @Parameter(in = ParameterIn.QUERY,
                    description = "Limit results to top N countries.",
                    example = "10") Integer limit
    );
}
