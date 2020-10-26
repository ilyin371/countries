package com.countries.services.query;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;
import lombok.val;

import java.util.LinkedList;
import java.util.List;

@Getter
@With
@NoArgsConstructor
@AllArgsConstructor
public class Query {

    List<Filter> filters = new LinkedList<>();
    Field sortBy = Field.NAME;
    SortOrder order = SortOrder.ASC;
    int limit = 100;

    public static Query withFilter(Field field, String value) {
        val query = new Query();
        query.filters.add(Filter.of(field, value));
        return query;
    }

}
