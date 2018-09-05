package com.example.quantity;

import com.example.quantity.unit.Unit;

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
        int result = Math.addExact(value, opedand(other));
        return new Quantity(result, unit);
    }

    public Quantity subtract(Quantity other) {
        int result = Math.subtractExact(value, opedand(other));
        return new Quantity(result, unit);
    }

    public Quantity multiply(int multiplier) {
        int result = Math.multiplyExact(value, multiplier);
        return new Quantity(result, unit);
    }

    private int opedand(Quantity quantity) {
        if (unit.isEqualTo(quantity.unit))
            return quantity.value;
        else if (unit.isPiece())
            return Math.multiplyExact(quantity.value, quantity.unit.piece());
        else
            return Math.floorDiv(quantity.value, unit.piece());
    }

    @Override
    public String toString() {
        return String.format("%d", value);
    }
}
