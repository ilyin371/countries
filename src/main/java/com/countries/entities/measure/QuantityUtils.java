package com.countries.entities.measure;

import org.springframework.lang.Nullable;
import tech.units.indriya.ComparableQuantity;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Area;
import java.util.Optional;

public class QuantityUtils {

    public static boolean isEmpty(Quantity<?> quantity) {
        return quantity.getValue().doubleValue() == 0.0;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Quantity<T>> ComparableQuantity<T> emptyQuantity(Unit<T> unit) {
        return Quantities.getQuantity(0.0, unit);
    }

    public static <T extends Quantity<T>> Optional<Quantity<T>> maybe(Quantity<T> quantity) {
        return isEmpty(quantity) ? Optional.empty() : Optional.of(quantity);
    }

    public static ComparableQuantity<Area> area(@Nullable Number areaSqKm) {
        return Optional.ofNullable(areaSqKm)
                .map(area -> Quantities.getQuantity(area, Units.SQUARE_KILOMETER))
                .orElse(QuantityUtils.emptyQuantity(Units.SQUARE_KILOMETER));
    }
}
