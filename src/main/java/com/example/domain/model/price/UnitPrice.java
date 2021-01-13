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
        if (! quantity.isSameUnit(unit)) throw new IllegalArgumentException("単位が違う");
        return amount.multiply(quantity.intValue());
    }
}
