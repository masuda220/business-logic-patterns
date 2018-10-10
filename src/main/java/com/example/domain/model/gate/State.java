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
}
