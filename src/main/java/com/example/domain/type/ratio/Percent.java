package com.example.domain.type.ratio;

import java.util.Objects;

/**
 * 百分率
 */
public class Percent {
    int value;

    public Percent(int value) {
        this.value = value;
    }

    public Percent plus(Percent other) {
        int result = Math.addExact(value, other.value);
        return new Percent(result);
    }

    public Percent minus(Percent other) {
        int result = Math.subtractExact(value, other.value);
        return new Percent(result);
    }

    public int multiply(int other) {
        int percentResult = Math.multiplyExact(other, value);
        return (percentResult / 100) + 四捨五入の調整値(percentResult);
    }

    public long multiply(long other) {
        long percentResult = Math.multiplyExact(other, value);
        return (percentResult / 100) + 四捨五入の調整値(percentResult);
    }

    private int 四捨五入の調整値(long percentResult) {
        long 端数の絶対値 = Math.abs(percentResult % 100);
        return 端数の絶対値 >= 50 ? Long.signum(percentResult) : 0;
    }

    @Override
    public String toString() {
        return String.format("%d%%", value);
    }

    @Override
    public boolean equals(Object other) {
        Percent percent = (Percent) other;
        return value == percent.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
