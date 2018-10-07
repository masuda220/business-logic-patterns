package com.example.domain.type.state;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.example.domain.type.state.Event.*;
import static com.example.domain.type.state.State.*;

/**
 * 状態の許可するイベントを扱う
 */
class AllowedEvents {

    Map<State, Set<Event>> allowedEvents;

    AllowedEvents() {
        allowedEvents = new HashMap<>();
        allowedEvents.put(OPENED, EnumSet.of(CLOSE));
        allowedEvents.put(CLOSED, EnumSet.of(OPEN, LOCK));
        allowedEvents.put(LOCKED, EnumSet.of(UNLOCK));
    }

    boolean isAllowed(State state, Event event) {
        Set<Event> events = allowedEvents.get(state);
        return events.contains(event);
    }
}
