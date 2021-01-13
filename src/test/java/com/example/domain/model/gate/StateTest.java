package com.example.domain.model.gate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static com.example.domain.model.gate.Event.*;
import static com.example.domain.model.gate.State.*;

public class StateTest {

    Class<IllegalArgumentException> exception = IllegalArgumentException.class;

    @Test
    @DisplayName("OPENEDからの状態遷移")
    void testOccursFromOpenedByEnum() {
        assertAll("遷移できる",
                () -> assertEquals(CLOSED, OPENED.occurs(CLOSE))
        );

        assertAll("遷移できない",
                () -> assertThrows(exception, () -> OPENED.occurs(OPEN)),
                () -> assertThrows(exception, () -> OPENED.occurs(LOCK)),
                () -> assertThrows(exception, () -> OPENED.occurs(UNLOCK))
        );
    }

    @Test
    @DisplayName("CLOSEDからの状態遷移")
    void testOccursFromClosedByEnum() {
        assertAll("遷移できる",
                () -> Assertions.assertEquals(OPENED, CLOSED.occurs(OPEN)),
                () -> Assertions.assertEquals(LOCKED, CLOSED.occurs(LOCK))
        );
        assertAll("遷移できない",
                () -> assertThrows(exception, () -> CLOSED.occurs(CLOSE)),
                () -> assertThrows(exception, () -> CLOSED.occurs(UNLOCK))
        );
    }

    @Test
    @DisplayName("LOCKEDからの状態遷移")
    void testOccursFromLockedByEnum() {
        assertAll("遷移できる",
                () -> assertEquals(CLOSED, LOCKED.occurs(UNLOCK))
        );
        assertAll("遷移できない",
                () -> assertThrows(exception, () -> LOCKED.occurs(OPEN)),
                () -> assertThrows(exception, () -> LOCKED.occurs(CLOSE)),
                () -> assertThrows(exception, () -> LOCKED.occurs(LOCK))
        );
    }

    @Test
    @DisplayName("OPENEDの期待するイベントの判定")
    void testOpenedIsExpected() {
        assertAll("期待するイベント",
                () -> assertTrue(OPENED.isExpected(CLOSE))
        );
        assertAll("期待しないイベント",
                () -> assertFalse(OPENED.isExpected(OPEN)),
                () -> assertFalse(OPENED.isExpected(LOCK)),
                () -> assertFalse(OPENED.isExpected(UNLOCK))
        );
    }

    @Test
    @DisplayName("CLOSEDの期待するイベントの判定")
    void testClosedIsExpected() {
        assertAll("期待するイベント",
                () -> assertTrue(CLOSED.isExpected(OPEN)),
                () -> assertTrue(CLOSED.isExpected(LOCK))
        );
        assertAll("期待しないイベント",
                () -> assertFalse(CLOSED.isExpected(CLOSE)),
                () -> assertFalse(CLOSED.isExpected(UNLOCK))
        );
    }

    @Test
    @DisplayName("LOCKEDの期待するイベントの判定")
    void testLockedIsExpected() {
        assertAll("期待するイベント",
                () -> assertTrue(LOCKED.isExpected(UNLOCK))
        );
        assertAll("期待しないイベント",
                () -> assertFalse(LOCKED.isExpected(OPEN)),
                () -> assertFalse(LOCKED.isExpected(CLOSE)),
                () -> assertFalse(LOCKED.isExpected(LOCK))
        );
    }

    @Test
    @DisplayName("OPENEDの期待するイベント")
    void testOpenedExpectedEvents() {
        Event[] expected = {CLOSE};
        Event[] actual = OPENED.expectedEvents();
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("CLOSEDの期待するイベント")
    void testClosedExpectedEvents() {
        Event[] expected = {OPEN, LOCK};
        Event[] actual = CLOSED.expectedEvents();
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("LOCKEDの期待するイベント")
    void testLockedExpectedEvents() {
        Event[] expected = {UNLOCK};
        Event[] actual = LOCKED.expectedEvents();
        assertArrayEquals(expected, actual);
    }
}
