package com.example.domain.model.price;

import com.example.domain.type.money.Amount;
import com.example.domain.type.quantity.Quantity;
import com.example.domain.type.quantity.unit.Unit;

/**
 * 単価を扱う
 */
public class UnitPrice {

    Amount amount;

    Unit unit;

    public UnitPrice(Amount amount, Unit unit) {
        this.amount = amount;
        this.unit = unit;
    }

    public Amount multiply(Quantity quantity) {
        Quantity one = new Quantity(1, unit);
        return amount.multiply(one.toSameUnit(quantity));
    }

    public UnitPrice convertTo(Unit target) {
        return new UnitPrice(exchange(unit, target), target);
    }

    Amount exchange(Unit from, Unit to) {
        if (from.isEqualTo(to))
            return amount;

        if (to.isPiece())
            return amount.divideExact(from.piece());

        if (from.isPiece() && to.isBox())
            return amount.multiply(to.piece());

        throw new IllegalArgumentException();
    }

}
