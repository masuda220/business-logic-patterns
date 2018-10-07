package com.example.domain.type.state;

/**
 * 状態を扱う
 */
public enum State {
    OPENED,
    CLOSED,
    LOCKED;

    static AllowedEvents allowedEvents = new AllowedEvents();

    public State occurs(Event event) {
        if (isArrowed(event)) return event.next();
        throw new IllegalArgumentException();
    }

    boolean isArrowed(Event event) {
        return  allowedEvents.isAllowed(this, event);
    }
}
