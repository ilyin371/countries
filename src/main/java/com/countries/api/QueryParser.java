package com.countries.api;

import com.countries.services.query.Field;
import com.countries.services.query.Query;
import com.countries.services.query.SortOrder;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;
import java.util.stream.Collectors;

import static com.countries.services.query.Settings.DEFAULT_LIMIT;
import static com.countries.services.query.Settings.DEFAULT_ORDER;
import static com.countries.services.query.Settings.DEFAULT_SORT;
import static java.util.Map.entry;

@Component
public class QueryParser {

    private static final Logger log = LoggerFactory.getLogger(QueryParser.class);
    private final String SORT_BY = "sort-by";
    private final String ORDER = "order";
    private final String ORDER_ASC = "asc";
    private final String ORDER_DESC = "desc";
    private final String LIMIT = "limit";

    Map<Field, String> fieldToParam = Map.ofEntries(
            entry(Field.NAME, "name"),
            entry(Field.CAPITAL, "capital"),
            entry(Field.POPULATION, "population"),
            entry(Field.POPULATION_DENSITY, "population-density"),
            entry(Field.AREA, "area"),
            entry(Field.CURRENCY_CODE, "currency-code"),
            entry(Field.CURRENCY_NAME, "currency-name")
    );
    Map<String, Field> paramToField = fieldToParam.entrySet()
            .stream()
            .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

    public Query parse(ServerRequest request) {
        val query = new Query();
        val queryParams = request.queryParams();

        log.info("Query params: {}", queryParams);

        fieldToParam.forEach((field, param) -> {
            if (queryParams.containsKey(param)) {
                queryParams.get(param).stream().findFirst()
                        .map(filterValue -> query.addFilter(field, filterValue));
            }
        });

        val result = query
                .withSortBy(request.queryParam(SORT_BY)
                        .map(param -> paramToField.get(param))
                        .orElse(DEFAULT_SORT))
                .withOrder(request.queryParam(ORDER)
                        .map(order -> order.equals(ORDER_DESC) ? SortOrder.DESC : SortOrder.ASC)
                        .orElse(DEFAULT_ORDER))
                .withLimit(request.queryParam(LIMIT)
                        .map(Integer::parseInt)
                        .orElse(DEFAULT_LIMIT));

        log.info("Parsed query: {}", result);
        return result;
    }
}
