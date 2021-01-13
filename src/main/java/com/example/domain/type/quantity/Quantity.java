package com.example.domain.type.quantity;

import com.example.domain.type.money.Amount;
import com.example.domain.type.quantity.unit.Unit;

/**
 * 数量を扱う
 */
public class Quantity {
    int value;
    Unit unit;

    public Quantity(int value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public Quantity add(Quantity other) {
        ensureSameUnit(other);
        int result = Math.addExact(value, other.value);
        return new Quantity(result, unit);
    }

    public Quantity subtract(Quantity other) {
        ensureSameUnit(other);
        int result = Math.subtractExact(value, other.value);
        return new Quantity(result, unit);
    }

    public Quantity multiply(int multiplier) {
        int result = Math.multiplyExact(value, multiplier);
        return new Quantity(result, unit);
    }

    public int intValue() {
        return value;
    }

    public Quantity convert(Unit target) {
        int converted = unit.convert(value, target);
        return new Quantity(converted, target);
    }

    public boolean isSameUnit(Unit other) {
        return unit.equals(other);
    }

    private void ensureSameUnit(Quantity other) {
        if (unit.equals(other.unit)) return;
        throw new IllegalArgumentException("単位が違う");
    }

    @Override
    public boolean equals(Object o) {
        Quantity quantity = (Quantity) o;
        if (value != quantity.value) return false;
        return unit.equals(quantity.unit);
    }

    @Override
    public int hashCode() {
        return value + unit.hashCode();
    }

    @Override
    public String toString() {
        return unit.show(value);
    }

}
