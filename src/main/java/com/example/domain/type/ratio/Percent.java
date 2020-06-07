package com.example.domain.type.ratio;

/**
 * 百分率
 */
public class Percent {
    int value;

    enum Rounding {
        UP(99),
        HALF_UP(50),
        DOWN(0);

        int offset;

        Rounding(int offset) {
            this.offset = offset;
        }
    }

    public Percent(int value) {
        this.value = value;
    }

    public Percent multiply(long other) {
        int intValue = Math.toIntExact(other); // intの範囲だけを対象にする
        if( intValue > (Integer.MAX_VALUE / 100) ) throw new IllegalArgumentException("overflow");
        if(intValue < (Integer.MIN_VALUE / 100) ) throw new IllegalArgumentException("underflow");
        return new Percent(value * intValue);
    }

    int intValue(Rounding rounding) {
        return (value + rounding.offset) / 100;
    }

    public int roundUp() {
        return intValue(Rounding.UP);
    }

    public int round()  {
        return intValue(Rounding.HALF_UP);
    }

    public int roundDown() {
        return intValue(Rounding.DOWN);
    }

    @Override
    public String toString() {
        return String.format("%d%%", value);
    }
}
