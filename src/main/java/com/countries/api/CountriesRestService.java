package com.countries.api;

import com.countries.api.dto.CountryRecord;
import com.countries.api.query.Order;
import com.countries.api.query.QueryParams;
import com.countries.api.query.SortableField;
import com.countries.services.CountriesService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class CountriesRestService implements CountriesRestApi {

    private static final Logger log = LoggerFactory.getLogger(CountriesRestService.class);

    private final CountriesService service;
    private final CountryMapper countryMapper;
    private final QueryMapper queryMapper;

    @Override
    public Flux<CountryRecord> findByName(String namePattern) {
        log.info("Find by name: {}", namePattern);
        return service.findByName(namePattern).map(countryMapper::map);
    }

    @Override
    public Flux<CountryRecord> find(String currencyCode, SortableField sortBy, Order order, Integer limit) {

        val params = new QueryParams()
                .withCurrencyCode(currencyCode)
                .withSortBy(sortBy)
                .withOrder(order)
                .withLimit(limit);

        val query = queryMapper.map(params);
        return service.getCountries(query).map(countryMapper::map);
    }
}
