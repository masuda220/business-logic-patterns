package com.example.money;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmountTest {

    @Test()
    void add() {
        Amount one = new Amount(1);
        Amount two = new Amount(2);
        // TODO : equals() を実装後、Amount で比較する
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
    void divideAndRemainder() {
        Amount two = new Amount(2);
        Amount[] actual = two.divideAndRemainder(1);
        // TODO : equals() を実装後、assertArrayEquals で比較する
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
}