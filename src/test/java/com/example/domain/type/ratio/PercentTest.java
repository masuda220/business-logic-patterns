package com.example.domain.type.ratio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PercentTest {

    static Percent three = new Percent(3);
    static Percent five = new Percent(5);
    static Percent eight = new Percent(8);

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
    void multiply(int expect, Percent percent, int data) {
        assertEquals(expect, percent.multiply(data));
    }

    @ParameterizedTest
    @MethodSource("multiply")
    void multiply(long expect, Percent percent, long data) {
        assertEquals(expect, percent.multiply(data));
    }

    static Stream<Arguments> multiply() {
        return Stream.of(
                arguments(1, three, 31),
                arguments(2, five, 31),
                arguments(2, eight, 31)
        );
    }
}