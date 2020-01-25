package com.example.domain.type.ratio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PercentageTest {

    @Test
    void multiply() {
        Percentage percentage = new Percentage(8);
        assertEquals(9, percentage.multiply(124));
    }

    @Test
    void multiplyHalfUp() {
        Percentage percentage = new Percentage(8);
        assertEquals(10, percentage.multiplyHalfUp(119));
        assertEquals(9, percentage.multiplyHalfUp(118));
    }

    @Test
    void multiplyUp() {
        Percentage percentage = new Percentage(8);
        assertEquals(10, percentage.multiplyUp(124));
    }

    @Test
    void testToString() {
        Percentage percentage = new Percentage(8);
        assertEquals("8%", percentage.toString());
    }
}