package com.example.domain.type.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StateAndEventTest {

    @Test
    @DisplayName("LOCKEDからの状態遷移")
    void testTransitFromLockedByEnum() {
        State locked = State.LOCKED;
        assertAll(
                () -> assertEquals(State.OPENED, locked.occurs(Event.OPEN)),
                () -> assertThrows(IllegalArgumentException.class, () -> locked.occurs(Event.CLOSE)),
                () -> assertThrows(IllegalArgumentException.class, () -> locked.occurs(Event.LOCK))
        );
    }

    @Test
    @DisplayName("OPENEDからの状態遷移")
    void testTransitFromOpenedByEnum() {
        State opened = State.OPENED;
        assertAll(
                () -> assertEquals(State.CLOSED, opened.occurs(Event.CLOSE)),
                () -> assertEquals(State.LOCKED, opened.occurs(Event.LOCK)),
                () -> assertThrows(IllegalArgumentException.class, () -> opened.occurs(Event.OPEN))
        );
    }

    @Test
    @DisplayName("CLOSEDからの状態遷移")
    void testTransitFromClosedByEnum() {
        State closed = State.CLOSED;
        assertAll(
                () -> assertEquals(State.OPENED, closed.occurs(Event.OPEN)),
                () -> assertThrows(IllegalArgumentException.class, () -> closed.occurs(Event.LOCK)),
                () -> assertThrows(IllegalArgumentException.class, () -> closed.occurs(Event.CLOSE))
        );
    }
}
