package com.example.domain.model.gate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static com.example.domain.model.gate.Event.*;
import static com.example.domain.model.gate.State.*;

public class StateTest {

    Class exception = IllegalArgumentException.class;

    @Test
    @DisplayName("OPENEDからの状態遷移")
    void testOccursFromOpenedByEnum() {
        assertAll(
                () -> assertThrows(exception, () -> OPENED.occurs(OPEN)),
                () -> assertEquals(CLOSED, OPENED.occurs(CLOSE)),
                () -> assertThrows(exception, () -> OPENED.occurs(LOCK)),
                () -> assertThrows(exception, () -> OPENED.occurs(UNLOCK))
        );
    }

    @Test
    @DisplayName("CLOSEDからの状態遷移")
    void testOccursFromClosedByEnum() {
        assertAll(
                () -> Assertions.assertEquals(OPENED, CLOSED.occurs(OPEN)),
                () -> assertThrows(exception, () -> CLOSED.occurs(CLOSE)),
                () -> Assertions.assertEquals(LOCKED, CLOSED.occurs(LOCK)),
                () -> assertThrows(exception, () -> CLOSED.occurs(UNLOCK))
        );
    }

    @Test
    @DisplayName("LOCKEDからの状態遷移")
    void testOccursFromLockedByEnum() {
        assertAll(
                () -> assertThrows(exception, () -> LOCKED.occurs(OPEN)),
                () -> assertThrows(exception, () -> LOCKED.occurs(CLOSE)),
                () -> assertThrows(exception, () -> LOCKED.occurs(LOCK)),
                () -> assertEquals(CLOSED, LOCKED.occurs(UNLOCK))
        );
    }

    @Test
    @DisplayName("OPENEDの期待するイベントの判定")
    void testOpenedIsExpected() {
        assertAll(
                () -> assertFalse(OPENED.isExpected(OPEN)),
                () -> assertTrue(OPENED.isExpected(CLOSE)),
                () -> assertFalse(OPENED.isExpected(LOCK)),
                () -> assertFalse(OPENED.isExpected(UNLOCK))
        );
    }

    @Test
    @DisplayName("CLOSEDの期待するイベントの判定")
    void testClosedIsExpected() {
        assertAll(
                () -> assertTrue(CLOSED.isExpected(OPEN)),
                () -> assertFalse(CLOSED.isExpected(CLOSE)),
                () -> assertTrue(CLOSED.isExpected(LOCK)),
                () -> assertFalse(CLOSED.isExpected(UNLOCK))
        );
    }

    @Test
    @DisplayName("LOCKEDの期待するイベントの判定")
    void testLockedIsExpected() {
        assertAll(
                () -> assertFalse(LOCKED.isExpected(OPEN)),
                () -> assertFalse(LOCKED.isExpected(CLOSE)),
                () -> assertFalse(LOCKED.isExpected(LOCK)),
                () -> assertTrue(LOCKED.isExpected(UNLOCK))
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
