# Countries REST API

Spring Boot application that consumes data from https://restcountries.eu and exposes some
results via REST API.
Area of interest: only countries in the European Union. Fields: name, capital, currencies,
population.

## Dependencies

 * JDK 11 (make sure `JAVA_HOME` is set correctly)
 * Maven

## Build

 * Run `./mvnw clean install` to build the application.
 * Run `./mvnw spring-boot:run` to run.

## Usage

For detailed REST API description please see the generated documentation at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

Required use cases examples:

 * Top 10 countries with the biggest population density (people / square km):
   * [/countries?sortBy=populationDensity&order=desc&limit=10](http://localhost:8080/countries?sortBy=populationDensity&order=desc&limit=10) 

 * All the countries that have the given currency: 
   *  [/countries?currencyCode=DKK](http://localhost:8080/countries?currencyCode=DKK)
   *  [/countries?currencyCode=EUR](http://localhost:8080/countries?currencyCode=EUR)
   
 * All the countries that correspond to a given pattern: 
    *  [/countries/Spain](http://localhost:8080/countries/Spain) 
    *  [/countries/S*n](http://localhost:8080/countries/S*n) 
    
## TODO

  - [ ] Unit test HTTP request params to internal API mapping.
  - [ ] Cache external service response.
  - [ ] Add circuit breaker to external service adapter.
  - [ ] Map exceptions to HTTP response codes.
  - [ ] Request params validation.
  - [ ] Add sorting/filtering params to `countries/{name}` path.
  - [ ] Integration tests.
  - [ ] Optional: try embedded reactive CRUD repository instead of custom stream based collection filtering/sorting.
