package com.example.domain.type.money;

import java.math.BigDecimal;

/**
 * 小数点以下4桁までの金額を表します。<br>
 * DecimalAmount クラスは {@link java.lang.Long} の整数値と 0から4までの小数点以下を表すスケールで構成されます。<br>
 * DecimalAmount で表される金額は {@link java.lang.Long#MAX_VALUE}×10 <sup>-scale</sup> から
 * {@link java.lang.Long#MIN_VALUE}×10 <sup>-scale</sup> までで、
 * スケールが 0 の場合は、 {@link java.lang.Long#MAX_VALUE} から {@link java.lang.Long#MIN_VALUE} までになります。<br>
 *
 * 加算、減算は、異なるスケールの演算が可能ですが、乗算、除算は乗数、除数は整数値のみとなります。
 *
 * <table>
 *     <caption>算術演算の結果で優先されるスケール</caption>
 *     <tr><th>演算</th><th>優先される結果のスケール</th></tr>
 *     <tr><td>加算</td><td>max(addend.scale, augend.scale)</td></tr>
 *     <tr><td>減算</td><td>max(minuend.scale, subtrahend.scale)</td></tr>
 *     <tr><td>乗算</td><td>multiplier.scale</td></tr>
 *     <tr><td>除算</td><td>dividend.scale</td></tr>
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
        BigDecimal result = value.divide(BigDecimal.valueOf(divisor), BigDecimal.ROUND_HALF_UP);
        return new DecimalAmount(result);
    }

    public Amount toAmount() {
        return new Amount(value.setScale(0, BigDecimal.ROUND_HALF_UP).longValue());
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
