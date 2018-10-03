package com.example.domain.type.state;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum State {
    LOCKED,
    OPENED,
    CLOSED;

    static Map<State, Set<Event>> allowedEvents;

    static {
        allowedEvents = new HashMap<>();
        allowedEvents.put(OPENED, EnumSet.of(Event.CLOSE, Event.LOCK));
        allowedEvents.put(LOCKED, EnumSet.of(Event.OPEN));
        allowedEvents.put(CLOSED, EnumSet.of(Event.OPEN));
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
