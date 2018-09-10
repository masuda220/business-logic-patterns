package com.example.domain.type.price;

import com.example.domain.type.money.Amount;
import com.example.domain.type.quantity.Quantity;
import com.example.domain.type.quantity.unit.Unit;

/**
 * 合計金額を扱う
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

}
