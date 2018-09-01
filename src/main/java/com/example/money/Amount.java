package com.example.money;

/**
 * 金額を扱う
 */
public class Amount {

    long value;

    public Amount(long value) {
        this.value = value;
    }

    public Amount add(Amount other) {
        long result = Math.addExact(value, other.value);
        return new Amount(result);
    }
}
