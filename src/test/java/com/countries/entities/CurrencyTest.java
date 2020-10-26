package com.countries.entities;

import com.countries.entities.currency.Currency;
import com.countries.entities.currency.InvalidCurrencyException;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CurrencyTest {

    @Test
    void getEuro() {

        val currency = Currency.of("EUR");

        assertThat(currency.getCode()).isEqualTo("EUR");
        assertThat(currency.getName()).isEqualTo("Euro");
        assertThat(currency.getSymbol()).isEqualTo("€");
    }

    @Test
    void getManxPound() {

        val currency = Currency.of("IMP");

        assertThat(currency.getCode()).isEqualTo("IMP");
        assertThat(currency.getName()).isEqualTo("Manx pound");
        assertThat(currency.getSymbol()).isEqualTo("£");
    }

    @Test
    void shouldThrowOnInvalidCode() {
        assertThatThrownBy(() -> Currency.of("INVALID"))
            .isInstanceOf(InvalidCurrencyException.class);
        }
    }
