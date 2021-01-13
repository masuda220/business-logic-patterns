package com.example.domain.type.money;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 小数点以下4桁までの金額を表します。<br>
 * DecimalAmount クラスは {@link java.lang.Long} の整数値 (unscaled value) と0から4までの小数点以下の桁数(scale)で構成します。<br>
 * DecimalAmount で表わす金額は {@link java.lang.Long#MAX_VALUE}×10 <sup>-scale</sup> から
 * {@link java.lang.Long#MIN_VALUE}×10 <sup>-scale</sup> の範囲です。<br>
 * スケールが0の場合に表わす金額は、 {@link java.lang.Long#MAX_VALUE} から {@link java.lang.Long#MIN_VALUE} の範囲です。<br>
 * <br>
 * 加算と減算は、異なるスケールの演算が可能です。<br>
 * 乗算の乗数は整数値のみです。<br>
 * 除算の除数は整数値のみです。<br>
 * <br>
 * <table>
 *     <caption>算術演算の結果で優先されるスケール</caption>
 *     <tr><th>演算</th><th>優先される結果のスケール</th><th>説明</th></tr>
 *     <tr><td>加算</td><td>max(addend.scale, augend.scale)</td><td>加数のスケールと被加数のスケールのうちで大きい方のスケール</td></tr>
 *     <tr><td>減算</td><td>max(minuend.scale, subtrahend.scale)</td><td>減数のスケールと被減数のスケールのうちで大きい方のスケール</td></tr>
 *     <tr><td>乗算</td><td>multiplier.scale</td><td>被乗数のスケール</td></tr>
 *     <tr><td>除算</td><td>dividend.scale</td><td>被除数のスケール</td></tr>
 * </table>
 */
public class DecimalAmount {

    final static int MAX_SCALE = 4;

    BigDecimal value;

    private DecimalAmount(BigDecimal source) {
        if (overMaxValue(source)) throw new ArithmeticException();
        if (lessMinValue(source)) throw new ArithmeticException();
        this.value = source;
    }

    static DecimalAmount valueOf(long longValue) {
        return new DecimalAmount(BigDecimal.valueOf(longValue));
    }

    public static DecimalAmount valueOf(Amount amount) {
        return DecimalAmount.valueOf(amount.value);
    }

    public static  DecimalAmount valueOf(String source) {
        BigDecimal bigDecimalValue = new BigDecimal(source);
        if (bigDecimalValue.scale() > MAX_SCALE) throw new ArithmeticException();
        return new DecimalAmount(bigDecimalValue);
    }

    public DecimalAmount add(DecimalAmount augend) {
        BigDecimal result = value.add(augend.value);
        return new DecimalAmount(result);
    }

    public DecimalAmount subtract(DecimalAmount subtrahend) {
        BigDecimal result = value.subtract(subtrahend.value);
        return new DecimalAmount(result);
    }

    public DecimalAmount multiply(int multiplicand) {
        BigDecimal result = value.multiply(BigDecimal.valueOf(multiplicand));
        return new DecimalAmount(result);
    }

    public DecimalAmount divide(int divisor) {
        BigDecimal result = value.divide(BigDecimal.valueOf(divisor), RoundingMode.HALF_UP);
        return new DecimalAmount(result);
    }

    public Amount toAmount() {
        return new Amount(value.setScale(0, RoundingMode.HALF_UP).longValue());
    }

    boolean overMaxValue(BigDecimal other) {
        return maxValue(other).compareTo(other) < 0;
    }

    boolean lessMinValue(BigDecimal other) {
        return minValue(other).compareTo(other) > 0;
    }

    BigDecimal maxValue(BigDecimal source) {
        BigDecimal longMaxValue = BigDecimal.valueOf(Long.MAX_VALUE);
        return longMaxValue.movePointLeft(source.scale());
    }

    BigDecimal minValue(BigDecimal source) {
        BigDecimal longMaxValue = BigDecimal.valueOf(Long.MIN_VALUE);
        return longMaxValue.movePointLeft(source.scale());
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
