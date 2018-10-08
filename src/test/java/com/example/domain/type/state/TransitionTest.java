package com.example.domain.type.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.example.domain.type.state.Event.*;
import static com.example.domain.type.state.State.*;
import static org.junit.jupiter.api.Assertions.*;

public class TransitionTest {

    Class exception = IllegalArgumentException.class;

    @Test
    @DisplayName("OPENEDからの状態遷移")
    void testTransitFromOpenedByEnum() {
        State opened = State.OPENED;
        assertAll(
                () -> assertThrows(exception, () -> opened.occurs(OPEN)),
                () -> assertEquals(CLOSED, opened.occurs(CLOSE)),
                () -> assertThrows(exception, () -> opened.occurs(LOCK)),
                () -> assertThrows(exception, () -> opened.occurs(UNLOCK))
        );
    }

    @Test
    @DisplayName("CLOSEDからの状態遷移")
    void testTransitFromClosedByEnum() {
        State closed = State.CLOSED;
        assertAll(
                () -> assertEquals(OPENED, closed.occurs(OPEN)),
                () -> assertThrows(exception, () -> closed.occurs(CLOSE)),
                () -> assertEquals(LOCKED, closed.occurs(LOCK)),
                () -> assertThrows(exception, () -> closed.occurs(UNLOCK))
        );
    }

    @Test
    @DisplayName("LOCKEDからの状態遷移")
    void testTransitFromLockedByEnum() {
        State locked = LOCKED;
        assertAll(
                () -> assertThrows(exception, () -> locked.occurs(OPEN)),
                () -> assertThrows(exception, () -> locked.occurs(CLOSE)),
                () -> assertThrows(exception, () -> locked.occurs(LOCK)),
                () -> assertEquals(CLOSED, locked.occurs(UNLOCK))
        );
    }
}
