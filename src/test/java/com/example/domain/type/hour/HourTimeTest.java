package com.example.domain.type.hour;

import static org.junit.jupiter.api.Assertions.*;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HourTimeTest {

    @Test()
    @DisplayName("コンストラクタテスト")
    void constructor() {
        HourTime actual = new HourTime(12, 34);
        assertEquals(12, actual.value.getHour());
        assertEquals(34, actual.value.getMinute());
    }

    @Test()
    void string() {
        HourTime actual = new HourTime(12, 34);
        assertEquals("12:34", actual.toString());
    }
}