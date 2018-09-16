package com.example.domain.type.money;

import java.math.BigDecimal;

public class DecimalAmount {

    final static BigDecimal MAX_VALUE = BigDecimal.valueOf(Long.MAX_VALUE);
    final static BigDecimal MIN_VALUE = BigDecimal.valueOf(Long.MIN_VALUE);
    final static int DEFAULT_SCALE = 2;

    BigDecimal value;

    private DecimalAmount(BigDecimal value) {
        if (MAX_VALUE.compareTo(value) < 0) throw new ArithmeticException();
        if (MIN_VALUE.compareTo(value) > 0) throw new ArithmeticException();
        this.value = value.setScale(DEFAULT_SCALE);
    }

    static DecimalAmount valueOf(long val) {
        return new DecimalAmount(BigDecimal.valueOf(val));
    }

    public static DecimalAmount valueOf(Amount val) {
        return DecimalAmount.valueOf(val.value);
    }

    public static  DecimalAmount valueOf(String val) {
        BigDecimal bigDecimalValue = new BigDecimal(val);
        if (bigDecimalValue.scale() > DEFAULT_SCALE) throw new ArithmeticException();
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

    @Override
    public String toString() {
        return value.toString();
    }
}
