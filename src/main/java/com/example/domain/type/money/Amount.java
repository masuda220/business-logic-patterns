package com.example.domain.type.money;

import java.util.Arrays;
import java.util.Collection;

/**
 * 金額を扱う
 */
public class Amount {

    long value;

    public Amount(long value) {
        this.value = value;
    }

    public static Amount from(Amount... amounts) {
        return new Amount(0).addAll(amounts);
    }

    public static Amount from(Collection<Amount> amounts) {
        return new Amount(0).addAll(amounts);
    }

    public Amount add(Amount other) {
        long result = Math.addExact(value, other.value);
        return new Amount(result);
    }

    public Amount addAll(Amount... amounts) {
        return addAll(Arrays.asList(amounts));
    }

    public Amount addAll(Collection<Amount> amounts) {
        Amount total = this;
        for (Amount amount : amounts) total = total.add(amount);
        return total;
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

    public boolean isGreaterThan(Amount other) {
        return value > other.value;
    }

    public boolean isGreaterOrEqualTo(Amount other) {
        return value >= other.value;
    }

    public boolean isLessThan(Amount other) {
        return value < other.value;
    }

    public boolean isLessOrEqualTo(Amount other) {
        return value <= other.value;
    }

    Amount divide(int divisor) {
        return new Amount(value / divisor);
    }

    Amount remainder(int divisor) {
        return new Amount(value % divisor);
    }
}
