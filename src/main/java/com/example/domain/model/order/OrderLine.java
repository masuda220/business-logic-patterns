package com.example.domain.model.order;

import com.example.domain.model.price.UnitPrice;
import com.example.domain.type.money.Amount;
import com.example.domain.type.quantity.Quantity;

public class OrderLine {
    // product
    UnitPrice unitPrice;
    Quantity quantity;

    public OrderLine(UnitPrice unitPrice, Quantity quantity) {
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public Amount amount() {
        return unitPrice.multiply(quantity);
    }
}
