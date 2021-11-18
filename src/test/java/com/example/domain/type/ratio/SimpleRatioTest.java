package com.example.domain.type.ratio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleRatioTest {

    // 生成時の制約

    @Test
    void one() {
        SimpleRatio result = SimpleRatio.from("1.00000");
        assertEquals(100_000, result.intValue());
    }

    @Test
    void valueOverOne() {
        assertThrows(ArithmeticException.class, () -> SimpleRatio.from("1.00001"));
    }

    @Test
    void scaleTooLong() {
        assertThrows(ArithmeticException.class, () -> SimpleRatio.from("0.000001"));
    }

    @Test
    void scaleTooShort() {
        assertThrows(ArithmeticException.class, () -> SimpleRatio.from("0.0001"));
    }

    // 掛け算
    @Test
    void multiply() {
        SimpleRatio oneRatio = SimpleRatio.from("0.99999");
        SimpleRatio anotherRatio = SimpleRatio.from("0.99999");
        SimpleRatio expected = SimpleRatio.from("0.99998");
        assertEquals(expected.整数値, oneRatio.multiply(anotherRatio).整数値);
    }
}