package com.example.domain.type.state;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.example.domain.type.state.Event.*;

/**
 * 状態を扱う
 */
public enum State {
    OPENED,
    CLOSED,
    LOCKED;

    static Map<State, Set<Event>> allowedEvents;

    static {
        allowedEvents = new HashMap<>();
        allowedEvents.put(OPENED, EnumSet.of(CLOSE));
        allowedEvents.put(CLOSED, EnumSet.of(OPEN, LOCK));
        allowedEvents.put(LOCKED, EnumSet.of(UNLOCK));
    }

    public State occurs(Event event) {
        if (isArrowed(event)) return event.next();
        throw new IllegalArgumentException();
    }

    boolean isArrowed(Event event) {
        Set<Event> events = allowedEvents.get(this);
        return events.contains(event);
    }
}
