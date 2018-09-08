package com.example.money;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmountTest {

    @Test()
    void add() {
        Amount one = new Amount(1);
        Amount two = new Amount(2);
        assertTrue(new Amount(3).isEqualTo(one.add(two)));
        assertEquals(new Amount(3).value, one.add(two).value, "1 + 2 = 3");
        Amount three = new Amount(3);
        assertEquals(new Amount(5).value, three.add(two).value, "3 + 2 = 5");
    }

    @Test()
    void addOverflow() {
        Amount max = new Amount(Long.MAX_VALUE);
        Amount one = new Amount(1);
        assertThrows(ArithmeticException.class, () -> max.add(one),"加算オーバーフロー");
    }

    @Test()
    void subtract() {
        Amount one = new Amount(1);
        Amount two = new Amount(2);
        assertEquals(new Amount(1).value, two.subtract(one).value, "2 - 1 = 1");
        Amount three = new Amount(3);
        assertEquals(new Amount(-1).value, two.subtract(three).value, "2 - 3 = -1");
    }

    @Test()
    void subtractOverflow() {
        Amount min = new Amount(Long.MIN_VALUE);
        Amount one = new Amount(1);
        assertThrows(ArithmeticException.class, () -> min.subtract(one),"減算オーバーフロー");
    }

    @Test()
    void multiply() {
        Amount one = new Amount(1);
        Amount actual = one.multiply(2);
        assertEquals(new Amount(2).value, actual.value, "1 * 2 = 2");

        Amount three = new Amount(3);
        actual = three.multiply(4);
        assertEquals(new Amount(12).value, actual.value, "3 * 4 = 12");
    }

    @Test()
    void multiplyOverflow() {
        Amount max = new Amount(Long.MAX_VALUE);
        assertThrows(ArithmeticException.class, () -> max.multiply(2),"乗算オーバーフロー");
    }

    @Test()
    void divideExact() {
        Amount two = new Amount(2);
        Amount actual = two.divideExact(1);
        assertEquals(new Amount(2).value, actual.value, "2 / 1 = 2");

        Amount six = new Amount(6);
        actual = six.divideExact(2);
        assertEquals(new Amount(3).value, actual.value, "6 / 2 = 3");
    }

    @Test()
    void indivisibleDivide() {
        Amount four = new Amount(4);
        assertThrows(ArithmeticException.class, () -> four.divideExact(3),"割り切れない除算");
    }

    @Test()
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

    @Test()
    void divideZero() {
        Amount one = new Amount(1);
        int divisorZero = 0;
        assertThrows(ArithmeticException.class, () -> one.divideAndRemainder(divisorZero),"0除算");
    }

    @Test()
    void isEqualTo() {
        Amount one = new Amount(1);
        Amount otherOne = new Amount(1);
        Amount two = new Amount(2);
        assertTrue(one.isEqualTo(otherOne), "同値");
        assertFalse(one.isEqualTo(two), "異なる値");
    }

    @Test()
    void isGreaterThan() {
        Amount one = new Amount(1);
        Amount two = new Amount(2);
        Amount three = new Amount(3);
        Amount otherTwo = new Amount(2);
        assertTrue(two.isGreaterThan(one), "2 は 1 より大きい");
        assertFalse(two.isGreaterThan(otherTwo), "2 は 2 より大きくない");
        assertFalse(two.isGreaterThan(three), "2 は 3 より大きくない");
    }

    @Test()
    void isGreaterOrEqualTo() {
        Amount one = new Amount(1);
        Amount two = new Amount(2);
        Amount three = new Amount(3);
        Amount otherTwo = new Amount(2);
        assertTrue(two.isGreaterOrEqualTo(one), "2 は 1 以上である");
        assertTrue(two.isGreaterOrEqualTo(otherTwo), "2 は 2 以上である");
        assertFalse(two.isGreaterOrEqualTo(three), "2 は 3 以上ではない");
    }

    @Test()
    void isLessThan() {
        Amount one = new Amount(1);
        Amount two = new Amount(2);
        Amount three = new Amount(3);
        Amount otherTwo = new Amount(2);
        assertFalse(two.isLessThan(one), "2 は 1 より小さくない");
        assertFalse(two.isLessThan(otherTwo), "2 は 2 より小さくない");
        assertTrue(two.isLessThan(three), "2 は 3 より小さい");
    }

    @Test()
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