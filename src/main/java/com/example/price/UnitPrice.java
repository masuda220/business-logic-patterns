package com.example.price;

import com.example.money.Amount;
import com.example.quantity.Quantity;
import com.example.quantity.unit.Unit;

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
