package com.example.domain.type.ratio;

/**
 * 千分率
 */
public class Permill {
    DecimalRatio ratio;

    private Permill(DecimalRatio ratio) {
        this.ratio = ratio;
    }

    public Permill plus(Permill other) {
        return new Permill(ratio.plus(other.ratio));
    }

    public Permill minus(Permill other) {
        return new Permill(ratio.minus(other.ratio));
    }

    public int multiply(int value) {
        return ratio.multiply(value);
    }

    public long multiply(long value) {
        return ratio.multiply(value);
    }

    public static Permill of(int value) {
        return new Permill(DecimalRatio.permill(value));
    }

    public static Permill of(int 分子, int 分母) {
        return new Permill(DecimalRatio.of(分子, 分母, 1_000));
    }
    public String showAsPercent() {
        return ratio.showWithOneDecimalPlace();
    }

    @Override
    public String toString() {
        return String.format("%d‰", ratio.分子);
    }

    @Override
    public boolean equals(Object other) {
        Permill permill = (Permill) other;
        return ratio.equals(permill.ratio);
    }

    @Override
    public int hashCode() {
        return ratio.hashCode();
    }
}
