package com.example.domain.type.state;

import static com.example.domain.type.state.State.*;

/**
 * イベントを扱う
 */
public enum Event {
    OPEN(OPENED),
    CLOSE(CLOSED),
    LOCK(LOCKED),
    UNLOCK(CLOSED);

    State nextState;

    Event(State nextState) {
        this.nextState = nextState;
    }

    State next() {
        return nextState;
    }
}
