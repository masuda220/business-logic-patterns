package com.example.domain.type.state;

/**
 * 状態を扱う
 */
public enum State {
    OPENED,
    CLOSED,
    LOCKED;

    static ExpectedEvents expectedEvents = new ExpectedEvents();

    public State occurs(Event event) {
        if (isExpected(event)) return event.next();
        throw new IllegalArgumentException();
    }

    boolean isExpected(Event event) {
        return  expectedEvents.isExpected(this, event);
    }
}
