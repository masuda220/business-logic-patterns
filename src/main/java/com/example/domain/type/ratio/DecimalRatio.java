package com.example.domain.type.ratio;

import java.util.Objects;

/**
 * 分母が１０の乗数の比率
 */
public class DecimalRatio {
    int 分子;
    int 分母;

    private DecimalRatio(int 分子, int 分母) {
        this.分子 = 分子;
        this.分母 = 分母;
    }

    public DecimalRatio plus(DecimalRatio other) {
        基準分母が同じことを保証する(other);
        int result = Math.addExact(分子, other.分子);
        return new DecimalRatio(result, 分母);
    }

    public DecimalRatio minus(DecimalRatio other) {
        基準分母が同じことを保証する(other);
        int result = Math.subtractExact(分子, other.分子);
        return new DecimalRatio(result, 分母);
    }

    public int multiply(int other) {
        int percentResult = Math.multiplyExact(other, 分子);
        return (percentResult / 分母) + 四捨五入の調整値(percentResult);
    }

    public long multiply(long other) {
        long percentResult = Math.multiplyExact(other, 分子);
        return (percentResult / 分母) + 四捨五入の調整値(percentResult);
    }

    private int 四捨五入の調整値(long percentResult) {
        long 端数の絶対値 = Math.abs(percentResult % 分母);
        return 端数の絶対値 >= (分母 / 2) ? Long.signum(percentResult) : 0;
    }

    private void 基準分母が同じことを保証する(DecimalRatio other) {
        if (分母 != other.分母) throw new IllegalArgumentException("分母が不一致");
    }

    public String showWithOneDecimalPlace() {
        return String.format("%d.%d", 分子 / 10, 分子 % 10);
    }

    public boolean isPercent() {
        return 分母 == 100;
    }

    public boolean isPermill() {
        return 分母 == 1_000;
    }

    public static DecimalRatio of(long 分子, long 分母, int 基準分母 ) {
        long 分子の倍数 = 分子 * 基準分母 * 10; // 一つ大きな桁で計算する（後で四捨五入をする）
        long 基準分母に対する分子 = 分子の倍数 / (分母 * 10);

        long 余り = 分子の倍数 % 分母;
        int 四捨五入 = (余り * 2) > 分母 ? 1 : 0 ;

        int int幅の分子 = Math.toIntExact(基準分母に対する分子) + 四捨五入;
        return new DecimalRatio(int幅の分子, 基準分母);
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
        return 分子 == decimalRatio.分子
                && 分母 == decimalRatio.分母;
    }

    @Override
    public int hashCode() {
        return Objects.hash(分子);
    }

    @Override
    public String toString() {
        return "DecimalRatio{" +
                "分子=" + 分子 +
                ", 基準分母=" + 分母 +
                '}';
    }
}
