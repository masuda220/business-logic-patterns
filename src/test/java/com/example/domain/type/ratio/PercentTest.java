package com.example.domain.type.ratio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PercentTest {

    static Percent eight = new Percent(8);
    static Percent value_952 = eight.multiply(119);
    static Percent value_944 = eight.multiply(118);

    @Test
    void multiply() {
        Percent multiplied = eight.multiply(7);
        assertEquals(56, multiplied.value);
    }

    @Test
    void roundUp() {
        Percent percent = new Percent(8);
        assertEquals(10, value_952.roundUp());
        assertEquals(10, value_944.roundUp());
    }

    @Test
    void round() {
        Percent percent = new Percent(8);
        assertEquals(10, value_952.round());
        assertEquals(9, value_944.round());
    }

    @Test
    void roundDown() {
        Percent percent = new Percent(8);
        assertEquals(9, value_952.roundDown());
        assertEquals(9, value_944.roundDown());
    }

    @Test
    void testToString() {
        Percent percent = new Percent(8);
        assertEquals("8%", percent.toString());
    }
}