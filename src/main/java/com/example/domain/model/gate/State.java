package com.example.domain.model.gate;

/**
 * 状態を扱う
 */
public enum State {
    OPENED,
    CLOSED,
    LOCKED;

    static Transitions transitions = new Transitions();

    public State occurs(Event event) {
        return transitions.next(this, event);
    }

    public boolean isExpected(Event event) {
        return transitions.isExpected(this, event);
    }

    public Event[] expectedEvents() {
        return transitions.expectedEvents(this);
    }
}
