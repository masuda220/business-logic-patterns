package com.example.domain.type.ratio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PercentTest {

    static Percent eightPercent = new Percent(8);
    static int halfOver = 119; // * 0.08 = 9.52
    static int halfUnder = 118; // * 0.08 = 9.44

    static Percent twoPercent = new Percent(2);
    static Percent tenPercent = new Percent(10);
    @Test
    void basicOperations() {
        assertEquals(tenPercent, eightPercent.plus(twoPercent));
        assertEquals(twoPercent, tenPercent.minus(eightPercent));
    }

    @Test
    void roundUp() {
        assertEquals(10, eightPercent.of_切り上げ(halfOver));
        assertEquals(10, eightPercent.of_切り上げ(halfUnder));
    }

    @Test
    void round() {
        assertEquals(10, eightPercent.longOf_四捨五入(halfOver));
        assertEquals(9, eightPercent.longOf_四捨五入(halfUnder));
    }

    @Test
    void roundDown() {
        assertEquals(9, eightPercent.longOf_切り捨て(halfOver));
        assertEquals(9, eightPercent.longOf_切り捨て(halfUnder));
    }

    @Test
    void longRoundUp() {
        assertEquals(10, eightPercent.longOf_切り上げ(halfOver));
        assertEquals(10, eightPercent.longOf_切り上げ(halfUnder));
    }

    @Test
    void longRound() {
        assertEquals(10, eightPercent.longOf_四捨五入(halfOver));
        assertEquals(9, eightPercent.longOf_四捨五入(halfUnder));
    }

    @Test
    void longRoundDown() {
        assertEquals(9, eightPercent.of_切り捨て(halfOver));
        assertEquals(9, eightPercent.of_切り捨て(halfUnder));
    }

    @Test
    void overFlow() {
        long value = Long.MAX_VALUE;
        assertThrows(ArithmeticException.class, () -> eightPercent.longOf_切り上げ(value));
        assertThrows(ArithmeticException.class, () -> eightPercent.longOf_切り上げ(value));
        assertThrows(ArithmeticException.class, () -> eightPercent.longOf_切り捨て(value));
    }

    @Test
    void underFlow() {
        long value = Long.MIN_VALUE;
        assertThrows(ArithmeticException.class, () -> eightPercent.longOf_切り上げ(value));
        assertThrows(ArithmeticException.class, () -> eightPercent.longOf_切り上げ(value));
        assertThrows(ArithmeticException.class, () -> eightPercent.longOf_切り捨て(value));
    }

    @Test
    void testToString() {
        Percent percent = new Percent(8);
        assertEquals("8%", percent.toString());
    }
}