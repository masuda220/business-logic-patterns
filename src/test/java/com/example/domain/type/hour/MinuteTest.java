package com.example.domain.type.hour;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DateTimeException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MinuteTest {

    @Test()
    @DisplayName("時間の引き算テスト")
    void subtract() {
        Minute minute23 = new Minute(23);
        Minute minute40 = new Minute(40);
        Minute actual = minute40.subtract(minute23);
        assertEquals(17, actual.value);
    }

    @Test()
    @DisplayName("マイナスになる時間の引き算で例外")
    void subtractFailure() {
        Minute minute23 = new Minute(23);
        Minute minute40 = new Minute(40);
        Throwable exception = assertThrows(DateTimeException.class, () -> minute23.subtract(minute40));
        assertEquals("Error of minus time.", exception.getMessage());
    }
}