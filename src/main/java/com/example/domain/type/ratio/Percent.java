package com.example.domain.type.ratio;

/**
 * 百分率
 */
public class Percent {
    DecimalRatio value;

    private Percent(DecimalRatio value) {
        this.value = value;
    }

    public Percent plus(Percent other) {
        return Percent.of(value.plus(other.value));
    }

    public Percent minus(Percent other) {
        return Percent.of(value.minus(other.value));
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

    public static Percent of(DecimalRatio decimalRatio) {
        return new Percent(decimalRatio);
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
