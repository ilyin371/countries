package com.countries.api.query;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.function.Function;

import static com.countries.api.CountriesRestApi.LIMIT;
import static com.countries.api.CountriesRestApi.ORDER;
import static com.countries.api.CountriesRestApi.SORT_BY;

@Component
public class QueryParamsParser {

    private static final Logger log = LoggerFactory.getLogger(QueryParamsParser.class);

    public QueryParams parse(ServerRequest request) {
        log.info("Parsing query params: {}", request.queryParams());

        val params = new ParamExtractor(request);
        val query = new QueryParams();
        val result = query
                .withCurrencyCode(params.get(LookupField.CURRENCY_CODE.toString(), s -> s))
                .withSortBy(params.get(SORT_BY, SortableField::of))
                .withOrder(params.get(ORDER, Order::of))
                .withLimit(params.get(LIMIT, Integer::parseInt));

        log.info("Parsed query: {}", result);
        return result;
    }

    @RequiredArgsConstructor
    private static class ParamExtractor {

        private final ServerRequest request;

        @Nullable
        public <T> T get(String paramName, Function<String, T> mapper) {
            return request.queryParam(paramName)
                    .map(mapper)
                    .orElse(null);
        }
    }
}
