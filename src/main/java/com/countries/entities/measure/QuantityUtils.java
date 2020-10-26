package com.countries.entities.measure;

import lombok.val;
import tech.units.indriya.ComparableQuantity;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import java.util.Optional;

public class QuantityUtils {

    public static boolean isEmpty(Quantity<?> quantity) {
        val symbol = quantity.getUnit().getSymbol();
        return Optional.ofNullable(symbol)
                .map(s -> s.equals(Units.NONE.getSymbol()))
                .orElse(false);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Quantity<T>> ComparableQuantity<T> emptyQuantity() {
        return (ComparableQuantity<T>) Quantities.getQuantity(0, Units.NONE);
    }

    public static <T extends Quantity<T>> Optional<Quantity<T>> maybe(Quantity<T> quantity) {
        return isEmpty(quantity) ? Optional.empty() : Optional.of(quantity);
    }
}
