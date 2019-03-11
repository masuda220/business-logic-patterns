package com.example.domain.type.hour;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExtendedHourTimeTest {

    @Test
    @DisplayName("文字列のparse")
    void create() {
        String expected = "26:05";
        ExtendedHourTime result = ExtendedHourTime.parse(expected);

        assertEquals(expected, result.show());
    }

    @Test
    @DisplayName("分換算")
    void toMinutes() {
        String source = "26:05";
        long expected = 26 * 60 + 5;

        ExtendedHourTime hourTime = ExtendedHourTime.parse(source);

        assertEquals(expected, hourTime.toMinutes());
    }
}