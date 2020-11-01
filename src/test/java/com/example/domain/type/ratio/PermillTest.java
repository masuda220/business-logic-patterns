package com.example.domain.type.ratio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PermillTest {

    static Permill three = Permill.of(3);
    static Permill five = Permill.of(5);
    static Permill eight = Permill.of(8);

    @Test
    void plus() {
        assertEquals(eight, three.plus(five));
    }

    @Test
    void minus() {
        assertEquals(three, eight.minus(five));
    }

    @ParameterizedTest
    @MethodSource
    void multiply(int expect, Permill Permill, int data) {
        assertEquals(expect, Permill.multiply(data));
    }

    @ParameterizedTest
    @MethodSource("multiply")
    void multiply(long expect, Permill Permill, long data) {
        assertEquals(expect, Permill.multiply(data));
    }

    static Stream<Arguments> multiply() {
        return Stream.of(
                arguments(1, three, 311),
                arguments(2, five, 312),
                arguments(2, eight, 312),
                arguments(3, eight, 313)
        );
    }

    @Test
    void showAsPercent() {
        assertEquals("8.1", Permill.of(81).showAsPercent());
    }
}