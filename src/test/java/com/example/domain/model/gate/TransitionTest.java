package com.example.domain.model.gate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransitionTest {

    Class exception = IllegalArgumentException.class;

    @Test
    @DisplayName("OPENEDからの状態遷移")
    void testTransitFromOpenedByEnum() {
        State opened = State.OPENED;
        assertAll(
                () -> assertThrows(exception, () -> opened.occurs(Event.OPEN)),
                () -> Assertions.assertEquals(State.CLOSED, opened.occurs(Event.CLOSE)),
                () -> assertThrows(exception, () -> opened.occurs(Event.LOCK)),
                () -> assertThrows(exception, () -> opened.occurs(Event.UNLOCK))
        );
    }

    @Test
    @DisplayName("CLOSEDからの状態遷移")
    void testTransitFromClosedByEnum() {
        State closed = State.CLOSED;
        assertAll(
                () -> Assertions.assertEquals(State.OPENED, closed.occurs(Event.OPEN)),
                () -> assertThrows(exception, () -> closed.occurs(Event.CLOSE)),
                () -> Assertions.assertEquals(State.LOCKED, closed.occurs(Event.LOCK)),
                () -> assertThrows(exception, () -> closed.occurs(Event.UNLOCK))
        );
    }

    @Test
    @DisplayName("LOCKEDからの状態遷移")
    void testTransitFromLockedByEnum() {
        State locked = State.LOCKED;
        assertAll(
                () -> assertThrows(exception, () -> locked.occurs(Event.OPEN)),
                () -> assertThrows(exception, () -> locked.occurs(Event.CLOSE)),
                () -> assertThrows(exception, () -> locked.occurs(Event.LOCK)),
                () -> Assertions.assertEquals(State.CLOSED, locked.occurs(Event.UNLOCK))
        );
    }
}
