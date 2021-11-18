package com.example.domain.type.ratio;

import java.math.BigDecimal;

/**
 * 比率計算のシンプルバージョン
 *
 * 値の範囲: 1以下 (1.00000を含む)
 * 桁数：小数点以下5桁固定
 * 丸め：小数点以下６桁を四捨五入
 * 演算：Ratio同士の掛け算のみ
 */
public class SimpleRatio {
    int 整数値;
    static final int 桁数 = 5;
    static final int 母数 = BigDecimal.ONE.scaleByPowerOfTen(桁数).intValueExact();

    private SimpleRatio(int 整数値) {
        this.整数値 = 整数値;
    }

    public int intValue() {
        return 整数値;
    }

    public SimpleRatio multiply(SimpleRatio other) {
        return multiply(other.整数値);
    }

    public SimpleRatio multiply(long other) {
        long unscaledResult = (long)整数値 * other;
        long adjustedResult = unscaledResult + (母数/2); // 小数点以下６桁目に5を追加
        long unscaledValueAdjusted = adjustedResult/母数;
        return new SimpleRatio(Math.toIntExact(unscaledValueAdjusted));
    }

    public static SimpleRatio from(String 文字列表現) {
        BigDecimal parsed = new BigDecimal(文字列表現);
        if (parsed.scale() != 桁数 ) throw new ArithmeticException("小数点以下の桁数が5桁ではない");
        if (parsed.compareTo(BigDecimal.ONE) == 大きい) throw new ArithmeticException("値の範囲が１を超えている");
        return new SimpleRatio(parsed.unscaledValue().intValueExact());
    }
    private static final int 大きい = 1; // compareToの返り値に名前をつける
}
