package com.countries.entities;

import tech.units.indriya.unit.BaseUnit;

import javax.measure.MetricPrefix;
import javax.measure.Unit;
import javax.measure.quantity.Area;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Length;

public class Units {

    static final BaseUnit<Dimensionless> PEOPLE = new BaseUnit<>("people");

    static final Unit<Length> KILOMETER = MetricPrefix.KILO(tech.units.indriya.unit.Units.METRE);

    public static final Unit<Area> SQUARE_KILOMETER = KILOMETER.multiply(KILOMETER).asType(Area.class);
}
