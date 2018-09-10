package com.example.domain.type.quantity;

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
        int result = Math.addExact(value, toSameUnit(other));
        return new Quantity(result, unit);
    }

    public Quantity subtract(Quantity other) {
        int result = Math.subtractExact(value, toSameUnit(other));
        return new Quantity(result, unit);
    }

    public Quantity multiply(int multiplier) {
        int result = Math.multiplyExact(value, multiplier);
        return new Quantity(result, unit);
    }

    public int toSameUnit(Quantity quantity) {
        if (unit.isEqualTo(quantity.unit))
            return quantity.value;
        else if (unit.isPiece())
            return Math.multiplyExact(quantity.value, quantity.unit.piece());

        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return String.format("%d", value);
    }

}
