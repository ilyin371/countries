package com.countries.api;

import com.countries.api.query.Order;
import com.countries.api.query.QueryParams;
import com.countries.api.query.SortableField;
import com.countries.services.query.Field;
import com.countries.services.query.Query;
import com.countries.services.query.SortOrder;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.countries.services.query.Settings.DEFAULT_LIMIT;
import static com.countries.services.query.Settings.DEFAULT_ORDER;
import static com.countries.services.query.Settings.DEFAULT_SORT;
import static java.util.Map.entry;

@Component
public class QueryMapper {

    private static final Logger log = LoggerFactory.getLogger(QueryMapper.class);

    Map<Field, SortableField> fieldToParam = Map.ofEntries(
            entry(Field.NAME, SortableField.NAME),
            entry(Field.CAPITAL, SortableField.CAPITAL),
            entry(Field.POPULATION, SortableField.POPULATION),
            entry(Field.POPULATION_DENSITY, SortableField.POPULATION_DENSITY),
            entry(Field.AREA, SortableField.AREA),
            entry(Field.CURRENCY_CODE, SortableField.CURRENCY_CODE),
            entry(Field.CURRENCY_NAME, SortableField.CURRENCY_NAME)
    );
    Map<SortableField, Field> paramToField = fieldToParam.entrySet()
            .stream()
            .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

    public Query map(QueryParams params) {
        val query = new Query();

        Optional.ofNullable(params.getCurrencyCode())
                .map(code -> query.addFilter(Field.CURRENCY_CODE, code));

        val result = query
                .withSortBy(Optional.ofNullable(params.getSortBy())
                        .map(param -> paramToField.get(param))
                        .orElse(DEFAULT_SORT))
                .withOrder(Optional.ofNullable(params.getOrder())
                        .map(order -> order.equals(Order.DESC) ? SortOrder.DESC : SortOrder.ASC)
                        .orElse(DEFAULT_ORDER))
                .withLimit(Optional.ofNullable(params.getLimit())
                        .orElse(DEFAULT_LIMIT));

        log.info("Mapped query: {}", result);
        return result;
    }
}
