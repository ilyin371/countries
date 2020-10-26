package com.countries.api.query;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;

@Getter
@With
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QueryParams {

    String currencyCode;

    SortableField sortBy;
    Order order;
    Integer limit;
}
