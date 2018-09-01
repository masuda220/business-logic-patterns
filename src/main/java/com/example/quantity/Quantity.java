package com.example.quantity;

/**
 * 数量を扱う
 */
public class Quantity {
    int value;

    public Quantity(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("%d", value);
    }
}
