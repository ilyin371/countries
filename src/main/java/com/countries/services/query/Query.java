package com.countries.services.query;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;
import lombok.val;

import java.util.LinkedList;
import java.util.List;

@Getter
@With
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Query {


    List<Filter> filters = new LinkedList<>();
    Field sortBy = Settings.DEFAULT_SORT;
    SortOrder order = Settings.DEFAULT_ORDER;
    int limit = Settings.DEFAULT_LIMIT;

    public static Query withFilter(Field field, String value) {
        val query = new Query();
        query.filters.add(Filter.of(field, value));
        return query;
    }

    public Query addFilter(Field field, String value) {
        this.filters.add(Filter.of(field, value));
        return this;
    }

}
