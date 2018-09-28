package com.example.domain.type.money;

import java.math.BigDecimal;

public class DecimalAmount {

    final static int MAX_SCALE = 4;

    BigDecimal value;

    private DecimalAmount(BigDecimal source) {
        if (overMaxValue(source)) throw new ArithmeticException();
        if (lessMinValue(source)) throw new ArithmeticException();
        this.value = source;
    }

    static DecimalAmount valueOf(long longValue) {
        return new DecimalAmount(BigDecimal.valueOf(longValue));
    }

    public static DecimalAmount valueOf(Amount amount) {
        return DecimalAmount.valueOf(amount.value);
    }

    public static  DecimalAmount valueOf(String source) {
        BigDecimal bigDecimalValue = new BigDecimal(source);
        if (bigDecimalValue.scale() > MAX_SCALE) throw new ArithmeticException();
        return new DecimalAmount(bigDecimalValue);
    }

    public DecimalAmount add(DecimalAmount augend) {
        BigDecimal result = value.add(augend.value);
        return new DecimalAmount(result);
    }

    public DecimalAmount subtract(DecimalAmount subtrahend) {
        BigDecimal result = value.subtract(subtrahend.value);
        return new DecimalAmount(result);
    }

    public DecimalAmount multiply(int multiplicand) {
        BigDecimal result = value.multiply(BigDecimal.valueOf(multiplicand));
        return new DecimalAmount(result);
    }

    public DecimalAmount divide(int divisor) {
        BigDecimal result = value.divide(BigDecimal.valueOf(divisor), BigDecimal.ROUND_HALF_UP);
        return new DecimalAmount(result);
    }

    public Amount toAmount() {
        return new Amount(value.setScale(0, BigDecimal.ROUND_HALF_UP).longValue());
    }

    boolean overMaxValue(BigDecimal other) {
        return maxValue(other).compareTo(other) < 0;
    }

    boolean lessMinValue(BigDecimal other) {
        return minValue(other).compareTo(other) > 0;
    }

    BigDecimal maxValue(BigDecimal source) {
        BigDecimal longMaxValue = BigDecimal.valueOf(Long.MAX_VALUE);
        return longMaxValue.movePointLeft(source.scale());
    }

    BigDecimal minValue(BigDecimal source) {
        BigDecimal longMaxValue = BigDecimal.valueOf(Long.MIN_VALUE);
        return longMaxValue.movePointLeft(source.scale());
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
