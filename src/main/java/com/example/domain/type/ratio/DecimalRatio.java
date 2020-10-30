package com.example.domain.type.ratio;

import java.util.Objects;

/**
 * 分母が１０の乗数の比率
 */
public class DecimalRatio {
    int value;
    int denominator;

    private DecimalRatio(int value, int denominator) {
        this.value = value;
        this.denominator = denominator;
    }

    public DecimalRatio plus(DecimalRatio other) {
        ensureSameDenominator(other);
        int result = Math.addExact(value, other.value);
        return new DecimalRatio(result, denominator);
    }

    public DecimalRatio minus(DecimalRatio other) {
        ensureSameDenominator(other);
        int result = Math.subtractExact(value, other.value);
        return new DecimalRatio(result, denominator);
    }

    public int multiply(int other) {
        int percentResult = Math.multiplyExact(other, value);
        return (percentResult / denominator) + 四捨五入の調整値(percentResult);
    }

    public long multiply(long other) {
        long percentResult = Math.multiplyExact(other, value);
        return (percentResult / denominator) + 四捨五入の調整値(percentResult);
    }

    private int 四捨五入の調整値(long percentResult) {
        long 端数の絶対値 = Math.abs(percentResult % denominator);
        return 端数の絶対値 >= (denominator / 2 ) ? Long.signum(percentResult) : 0;
    }

    private void ensureSameDenominator(DecimalRatio other) {
        if (denominator != other.denominator) throw new IllegalArgumentException("分母が不一致");
    }

    public static DecimalRatio percent(int value) {
        return new DecimalRatio(value, 100);
    }

    public static DecimalRatio permill(int value) {
        return new DecimalRatio(value, 1000);
    }

    @Override
    public boolean equals(Object other) {
        DecimalRatio decimalRatio = (DecimalRatio) other;
        return value == decimalRatio.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
