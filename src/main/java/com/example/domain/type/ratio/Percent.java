package com.example.domain.type.ratio;

import java.util.Objects;

/**
 * 百分率
 */
public class Percent {
    DecimalRatio value;

    private Percent(DecimalRatio value) {
        this.value = value;
    }

    public Percent plus(Percent other) {
        DecimalRatio result = value.plus(other.value);
        return new Percent(result);
    }

    public Percent minus(Percent other) {
        DecimalRatio result = value.minus(other.value);
        return new Percent(result);
    }

    public int multiply(int other) {
        return value.multiply(other);
    }

    public long multiply(long other) {
        return value.multiply(other);
    }

    public static Percent of(int value) {
        return new Percent(DecimalRatio.percent(value));
    }

    @Override
    public String toString() {
        return String.format("%d%%", value);
    }

    @Override
    public boolean equals(Object other) {
        Percent percent = (Percent) other;
        return value.equals(percent.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
