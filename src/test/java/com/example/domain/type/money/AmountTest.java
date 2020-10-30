package com.example.domain.type.money;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import com.example.domain.type.ratio.Percent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AmountTest {

    @Test
    @DisplayName("配列の合計")
    void fromByArray() {
        Amount actual = Amount.from(
                new Amount(1),
                new Amount(2),
                new Amount(3),
                new Amount(4));
        assertEquals(new Amount(10).value, actual.value, "1, 2, 3, 4 => 10");
    }

    @Test
    @DisplayName("要素数0の配列")
    void fromByNoArray() {
        Amount actual = Amount.from();
        assertEquals(new Amount(0).value, actual.value);
    }

    @Test
    @DisplayName("リストの合計")
    void fromByCollection() {
        Collection<Amount> amounts = Arrays.asList(
                new Amount(1),
                new Amount(2),
                new Amount(3),
                new Amount(4));
        Amount actual = Amount.from(amounts);
        assertEquals(new Amount(10).value, actual.value, "1, 2, 3, 4 => 10");
    }

    @Test
    @DisplayName("要素数0のリスト")
    void fromByNoCollection() {
        Collection<Amount> amounts = Arrays.asList();
        Amount actual = Amount.from(amounts);
        assertEquals(new Amount(0).value, actual.value);
    }

    @Test
    @DisplayName("加算")
    void add() {
        Amount one = new Amount(1);
        Amount two = new Amount(2);
        assertTrue(new Amount(3).isEqualTo(one.add(two)));
        assertEquals(new Amount(3).value, one.add(two).value, "1 + 2 = 3");
        Amount three = new Amount(3);
        assertEquals(new Amount(5).value, three.add(two).value, "3 + 2 = 5");
    }

    @Test
    @DisplayName("加算オーバーフロー")
    void addOverflow() {
        Amount max = new Amount(Long.MAX_VALUE);
        Amount one = new Amount(1);
        assertThrows(ArithmeticException.class, () -> max.add(one));
    }

    @Test
    @DisplayName("配列の加算")
    void addAllByArray() {
        Amount one = new Amount(1);
        Amount actual = one.addAll(
                new Amount(2),
                new Amount(3),
                new Amount(4));
        assertEquals(new Amount(10).value, actual.value, "1 + (2 + 3 + 4) = 10");
    }

    @Test
    @DisplayName("要素数0の配列の加算")
    void addAllByNoArray() {
        Amount one = new Amount(1);
        Amount actual = one.addAll();
        assertEquals(new Amount(1).value, actual.value, "1 + ( 要素数0の配列 ) = 1");
    }

    @Test
    @DisplayName("リストの加算")
    void addAllByCollection() {
        Amount one = new Amount(1);
        Collection<Amount> addends = Arrays.asList(
                new Amount(2),
                new Amount(3),
                new Amount(4));
        Amount actual = one.addAll(addends);
        assertEquals(new Amount(10).value, actual.value, "1 + (2 + 3 + 4) = 10");
    }

    @Test
    @DisplayName("要素数0のリストの加算")
    void addAllByNoCollection() {
        Amount one = new Amount(1);
        Collection<Amount> addends = Arrays.asList();
        Amount actual = one.addAll(addends);
        assertEquals(new Amount(1).value, actual.value, "1 + ( 要素数0のリスト ) = 1");
    }

    @Test
    @DisplayName("減算")
    void subtract() {
        Amount one = new Amount(1);
        Amount two = new Amount(2);
        assertEquals(new Amount(1).value, two.subtract(one).value, "2 - 1 = 1");
        Amount three = new Amount(3);
        assertEquals(new Amount(-1).value, two.subtract(three).value, "2 - 3 = -1");
    }

    @Test
    @DisplayName("減算オーバーフロー")
    void subtractOverflow() {
        Amount min = new Amount(Long.MIN_VALUE);
        Amount one = new Amount(1);
        assertThrows(ArithmeticException.class, () -> min.subtract(one));
    }

    @Test
    @DisplayName("乗算")
    void multiply() {
        Amount one = new Amount(1);
        Amount actual = one.multiply(2);
        assertEquals(new Amount(2).value, actual.value, "1 * 2 = 2");

        Amount three = new Amount(3);
        actual = three.multiply(4);
        assertEquals(new Amount(12).value, actual.value, "3 * 4 = 12");
    }

    @Test
    @DisplayName("乗算オーバーフロー")
    void multiplyOverflow() {
        Amount max = new Amount(Long.MAX_VALUE);
        assertThrows(ArithmeticException.class, () -> max.multiply(2));
    }

    @Test
    @DisplayName("パーセント計算")
    void percentOf() {
        Percent eightPercent = Percent.of(8);
        Amount amount = new Amount(118);
        assertEquals(9L,amount.multiply(eightPercent).value);
    }

    @Test
    @DisplayName("除算")
    void divideExact() {
        Amount two = new Amount(2);
        Amount actual = two.divideExact(1);
        assertEquals(new Amount(2).value, actual.value, "2 / 1 = 2");

        Amount six = new Amount(6);
        actual = six.divideExact(2);
        assertEquals(new Amount(3).value, actual.value, "6 / 2 = 3");
    }

    @Test
    @DisplayName("割り切れない除算による例外")
    void indivisibleDivide() {
        Amount four = new Amount(4);
        assertThrows(ArithmeticException.class, () -> four.divideExact(3));
    }

    @Test
    @DisplayName("余剰のある除算")
    void divideAndRemainder() {
        Amount two = new Amount(2);
        Amount[] actual = two.divideAndRemainder(1);
        assertEquals(2, actual.length, "{商、剰余}");
        assertEquals(new Amount(2).value, actual[0].value, "2 / 1 = 2");
        assertEquals(new Amount(0).value, actual[1].value, "2 % 1 = 0");

        Amount four = new Amount(4);
        actual = four.divideAndRemainder(3);
        assertEquals(2, actual.length, "{商、剰余}");
        assertEquals(new Amount(1).value, actual[0].value, "4 / 3 = 1");
        assertEquals(new Amount(1).value, actual[1].value, "4 % 3 = 1");
    }

    @Test
    @DisplayName("0除算による例外")
    void divideZero() {
        Amount one = new Amount(1);
        int divisorZero = 0;
        assertThrows(ArithmeticException.class, () -> one.divideAndRemainder(divisorZero));
    }

    @Test
    @DisplayName("イコール")
    void isEqualTo() {
        Amount one = new Amount(1);
        Amount otherOne = new Amount(1);
        Amount two = new Amount(2);
        assertTrue(one.isEqualTo(otherOne), "同値");
        assertFalse(one.isEqualTo(two), "異なる値");
    }

    @Test
    @DisplayName("大なり")
    void isGreaterThan() {
        Amount one = new Amount(1);
        Amount two = new Amount(2);
        Amount three = new Amount(3);
        Amount otherTwo = new Amount(2);
        assertTrue(two.isGreaterThan(one), "2 は 1 より大きい");
        assertFalse(two.isGreaterThan(otherTwo), "2 は 2 より大きくない");
        assertFalse(two.isGreaterThan(three), "2 は 3 より大きくない");
    }

    @Test
    @DisplayName("大なりイコール")
    void isGreaterOrEqualTo() {
        Amount one = new Amount(1);
        Amount two = new Amount(2);
        Amount three = new Amount(3);
        Amount otherTwo = new Amount(2);
        assertTrue(two.isGreaterOrEqualTo(one), "2 は 1 以上である");
        assertTrue(two.isGreaterOrEqualTo(otherTwo), "2 は 2 以上である");
        assertFalse(two.isGreaterOrEqualTo(three), "2 は 3 以上ではない");
    }

    @Test
    @DisplayName("小なり")
    void isLessThan() {
        Amount one = new Amount(1);
        Amount two = new Amount(2);
        Amount three = new Amount(3);
        Amount otherTwo = new Amount(2);
        assertFalse(two.isLessThan(one), "2 は 1 より小さくない");
        assertFalse(two.isLessThan(otherTwo), "2 は 2 より小さくない");
        assertTrue(two.isLessThan(three), "2 は 3 より小さい");
    }

    @Test
    @DisplayName("小なりイコール")
    void isLessOrEqualTo() {
        Amount one = new Amount(1);
        Amount two = new Amount(2);
        Amount three = new Amount(3);
        Amount otherTwo = new Amount(2);
        assertFalse(two.isLessOrEqualTo(one), "2 は 1 以下ではなし");
        assertTrue(two.isLessOrEqualTo(otherTwo), "2 は 2 以下である");
        assertTrue(two.isLessOrEqualTo(three), "2 は 3 以下である");
    }
}