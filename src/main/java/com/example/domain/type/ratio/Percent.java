package com.example.domain.type.ratio;

/**
 * 百分率
 */
public class Percent {
    DecimalRatio ratio;

    private Percent(DecimalRatio value) {
        this.ratio = value;
    }

    public Percent plus(Percent other) {
        return new Percent(ratio.plus(other.ratio));
    }

    public Percent minus(Percent other) {
        return new Percent(ratio.minus(other.ratio));
    }

    public int multiply(int value) {
        return ratio.multiply(value);
    }

    public long multiply(long value) {
        return ratio.multiply(value);
    }

    public static Percent of(int value) {
        return new Percent(DecimalRatio.percent(value));
    }

    @Override
    public String toString() {
        return String.format("%d%%", ratio);
    }

    @Override
    public boolean equals(Object other) {
        Percent percent = (Percent) other;
        return ratio.equals(percent.ratio);
    }

    @Override
    public int hashCode() {
        return ratio.hashCode();
    }
}
