package com.example.domain.type.state;

import java.util.HashMap;
import java.util.Map;

import static com.example.domain.type.state.Event.*;
import static com.example.domain.type.state.State.*;

/**
 * 次の状態を扱う
 */
class NextState {

    Map<Event, State> nextState;

    NextState() {
        nextState = new HashMap<>();
        nextState.put(OPEN, OPENED);
        nextState.put(CLOSE, CLOSED);
        nextState.put(LOCK, LOCKED);
        nextState.put(UNLOCK, CLOSED);
    }

    State next(Event event) {
        return nextState.get(event);
    }
}
