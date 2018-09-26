package com.example.domain.type.money;

import java.math.BigDecimal;

public class DecimalAmount {

    final static int MAX_SCALE = 4;

    BigDecimal value;

    private DecimalAmount(BigDecimal value) {
        if (overMaxValue(value)) throw new ArithmeticException();
        if (lessMinValue(value)) throw new ArithmeticException();
        this.value = value;
    }

    static DecimalAmount valueOf(long value) {
        return new DecimalAmount(BigDecimal.valueOf(value));
    }

    public static DecimalAmount valueOf(Amount value) {
        return DecimalAmount.valueOf(value.value);
    }

    public static  DecimalAmount valueOf(String value) {
        BigDecimal bigDecimalValue = new BigDecimal(value);
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

    boolean overMaxValue(BigDecimal value) {
        return maxValue(value).compareTo(value) < 0;
    }

    boolean lessMinValue(BigDecimal value) {
        return minValue(value).compareTo(value) > 0;
    }

    BigDecimal maxValue(BigDecimal value) {
        BigDecimal longMaxValue = BigDecimal.valueOf(Long.MAX_VALUE);
        return longMaxValue.movePointLeft(value.scale());
    }

    BigDecimal minValue(BigDecimal value) {
        BigDecimal longMaxValue = BigDecimal.valueOf(Long.MIN_VALUE);
        return longMaxValue.movePointLeft(value.scale());
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
