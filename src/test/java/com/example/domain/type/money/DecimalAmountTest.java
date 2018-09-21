package com.example.domain.type.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DecimalAmountTest {

    @Test
    @DisplayName("Amount から DecimalAmount を生成し、 toAmount で Amount に変換")
    void valueOfAmount() {
        Amount amount = new Amount(123456L);
        DecimalAmount actual = DecimalAmount.valueOf(amount);
        BigDecimal expected = new BigDecimal("123456.00");
        assertEquals(expected, actual.value);
        assertEquals(2, actual.value.scale(), "試作時の小数点以下桁数は2");
        assertEquals(amount.value, actual.toAmount().value, "toAmount でもとにAmountと同じ");
    }

    @Test
    @DisplayName("String から DecimalAmount を生成")
    void valueOfString() {
        BigDecimal expected = new BigDecimal("12345.67");
        DecimalAmount actual = DecimalAmount.valueOf("12345.67");
        assertEquals(expected, actual.value, "小数点以下2桁");

        expected = new BigDecimal("12345.60");
        actual = DecimalAmount.valueOf("12345.6");
        assertEquals(expected, actual.value, "小数点以下1桁 → 2桁に変更");

        expected = new BigDecimal("12345.00");
        actual = DecimalAmount.valueOf("12345");
        assertEquals(expected, actual.value, "整数 → 小数点以下2桁に変更");

        BigDecimal longMaxValue = BigDecimal.valueOf(Long.MAX_VALUE);
        BigDecimal expectedLongMax = longMaxValue.setScale(2);
        DecimalAmount actualLongMax = DecimalAmount.valueOf(longMaxValue.toString());
        assertEquals(expectedLongMax, actualLongMax.value, "Long MAX VALUE の小数点以下2桁");

        BigDecimal longMinValue = BigDecimal.valueOf(Long.MIN_VALUE);
        BigDecimal expectedLongMin = longMinValue.setScale(2);
        DecimalAmount actualLongMin = DecimalAmount.valueOf(longMinValue.toString());
        assertEquals(expectedLongMin, actualLongMin.value, "Long MIN VALUE の小数点以下2桁");
    }

    @Test
    @DisplayName("String から DecimalAmount を生成の失敗")
    void valueOfStringFailure() {
        assertThrows(NumberFormatException.class, () -> DecimalAmount.valueOf("ABDCEDG"), "数字以外");
        assertThrows(ArithmeticException.class, () -> DecimalAmount.valueOf("1234.123"), "小数点以下桁数オーバー");

        BigDecimal moreLongMax = BigDecimal.valueOf(Long.MAX_VALUE).add(new BigDecimal(1));
        assertThrows(ArithmeticException.class, () -> DecimalAmount.valueOf(moreLongMax.toString()), "Long 最大値超過");

        BigDecimal lessLongMin = BigDecimal.valueOf(Long.MIN_VALUE).subtract(new BigDecimal(1));
        assertThrows(ArithmeticException.class, () -> DecimalAmount.valueOf(lessLongMin.toString()), "Long 最小値未満");
    }

    @Test
    @DisplayName("toAmount の 四捨五入")
    void toAmount() {
        DecimalAmount roundUpValue = DecimalAmount.valueOf("1234.56");
        assertEquals(1235L, roundUpValue.toAmount().value);

        DecimalAmount roundDownValue = DecimalAmount.valueOf("98765.43");
        assertEquals(98765L, roundDownValue.toAmount().value);
    }

    @Test
    @DisplayName("加算")
    void add() {
        DecimalAmount addend = DecimalAmount.valueOf("1234.56");
        DecimalAmount augend = DecimalAmount.valueOf("0.44");
        DecimalAmount expected = DecimalAmount.valueOf("1235.00");
        DecimalAmount actual = addend.add(augend);
        assertEquals(expected.value, actual.value, "1234.56 + 0.44 = 1235.00");
    }

    @Test
    @DisplayName("加算オーバーフロー")
    void addOverflow() {
        DecimalAmount longMaxValue = DecimalAmount.valueOf(Long.MAX_VALUE);
        DecimalAmount zeroPointZeroOne = DecimalAmount.valueOf("0.01");
        assertThrows(ArithmeticException.class, () -> longMaxValue.add(zeroPointZeroOne));
    }

    @Test
    @DisplayName("減算")
    void subtract() {
        DecimalAmount minuend = DecimalAmount.valueOf("1234.56");
        DecimalAmount subtrahend = DecimalAmount.valueOf("0.56");
        DecimalAmount expected = DecimalAmount.valueOf("1234.00");
        DecimalAmount actual = minuend.subtract(subtrahend);
        assertEquals(expected.value, actual.value, "1234.56 - 0.56 = 1234.00");
    }

    @Test
    @DisplayName("減算オーバーフロー")
    void subtractOverflow() {
        DecimalAmount longMinValue = DecimalAmount.valueOf(Long.MIN_VALUE);
        DecimalAmount zeroPointZeroOne = DecimalAmount.valueOf("0.01");
        assertThrows(ArithmeticException.class, () -> longMinValue.subtract(zeroPointZeroOne));
    }

    @Test
    @DisplayName("乗算")
    void multiply() {
        DecimalAmount multiplier = DecimalAmount.valueOf("12345.67");
        int two = 2;
        BigDecimal expected = new BigDecimal("24691.34");
        DecimalAmount actual = multiplier.multiply(two);
        assertEquals(expected, actual.value);

        int ten = 10;
        BigDecimal expectedTenfold = new BigDecimal("123456.70");
        DecimalAmount actualTenfold = multiplier.multiply(ten);
        assertEquals(expectedTenfold, actualTenfold.value, "10倍しても小数点以下2桁");
    }

    @Test
    @DisplayName("乗算オーバーフロー")
    void multiplyOverflow() {
        DecimalAmount longMaxValue = DecimalAmount.valueOf(Long.MAX_VALUE);
        assertThrows(ArithmeticException.class, () -> longMaxValue.multiply(2));
    }

    @Test
    @DisplayName("除算")
    void divide() {
        DecimalAmount dividend = DecimalAmount.valueOf("12345.67");

        int divisorForRoundUp = 2;
        BigDecimal expectedRoundUp = new BigDecimal("6172.84");
        DecimalAmount actualRoundUp = dividend.divide(divisorForRoundUp);
        assertEquals(expectedRoundUp, actualRoundUp.value, "12345.67 / 2 = 6172.835 -> 6172.84");

        int divisorForRoundDown = 3;
        BigDecimal expectedRoundDown = new BigDecimal("4115.22");
        DecimalAmount actualRoundDown = dividend.divide(divisorForRoundDown);
        assertEquals(expectedRoundDown, actualRoundDown.value, "12345.67 / 3 = 4115.223.. -> 4115.22");
    }
}