package com.example.money;

import java.math.BigDecimal;

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

    public Amount subtract(Amount other) {
        long result = Math.subtractExact(value, other.value);
        return new Amount(result);
    }

    public Amount multiply(int multiplicand) {
        long result = Math.multiplyExact(value, multiplicand);
        return new Amount(result);
    }

    public Amount divideExact(int divisor) {
        if (value % divisor == 0) return divide(divisor);
        throw new ArithmeticException();
    }

    public Amount[] divideAndRemainder(int divisor) {
        Amount[] result = {
                divide(divisor),
                remainder(divisor)
        };
        return result;
    }

    public boolean isEqualTo(Amount other) {
        return value == other.value;
    }

    Amount divide(int divisor) {
        return new Amount(value / divisor);
    }

    Amount remainder(int divisor) {
        return new Amount(value % divisor);
    }
}
