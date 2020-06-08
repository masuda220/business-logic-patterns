package com.example.domain.type.ratio;

import java.util.Objects;

import static com.example.domain.type.ratio.Percent.Rounding.*;

/**
 * 百分率
 */
public class Percent {
    int value;

    enum Rounding {
        切り上げ(99),
        四捨五入(50),
        切り捨て(0);

        int offset;

        Rounding(int offset) {
            this.offset = offset;
        }
    }

    public Percent(int value) {
        this.value = value;
    }

    public Percent plus(Percent other) {
        return new Percent(value + other.value);
    }

    public Percent minus(Percent other) {
        return new Percent(value - other.value);
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

    // long範囲の演算
    private long longOf(long source, Rounding rounding) {
        long unscaled = Math.multiplyExact(source, value);
        long unscaledWithOffset = Math.addExact(unscaled, rounding.offset);
        return unscaledWithOffset / 100;
    }

    public long longOf_四捨五入(long source) {
        return longOf(source, 四捨五入);
    }

    public long longOf_切り上げ(long source) {
        return longOf(source, 切り上げ);
    }

    public long longOf_切り捨て(long source) {
        return longOf(source, 切り捨て);
    }

    // int範囲の演算
    private int of(int source, Rounding rounding) {
        long longResult = longOf(source, rounding);
        return Math.toIntExact(longResult);
    }

    public int of_四捨五入(int source) {
        return of(source, 四捨五入);
    }

    public int of_切り上げ(int source) {
        return of(source, 切り上げ);
    }

    public int of_切り捨て(int source) {
        return of(source, 切り捨て);
    }

    @Override
    public String toString() {
        return String.format("%d%%", value);
    }
}
