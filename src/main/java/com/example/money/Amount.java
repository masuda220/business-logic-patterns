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

    public Amount[] divideAndRemainder(int divisor) {
        Amount[] result = {
                divide(divisor),
                remainder(divisor)
        };
        return result;
    }

    Amount divide(int divisor) {
        BigDecimal dividend = new BigDecimal(this.value);
        BigDecimal result = dividend.divide(new BigDecimal(divisor), 0, BigDecimal.ROUND_DOWN);
        return new Amount(result.longValue());
    }

    Amount remainder(int divisor) {
        BigDecimal dividend = new BigDecimal(this.value);
        BigDecimal result = dividend.remainder(new BigDecimal(divisor));
        return new Amount(result.longValue());
    }
}
