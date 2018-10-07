package com.example.domain.type.state;

/**
 * イベントを扱う
 */
public enum Event {
    OPEN,
    CLOSE,
    LOCK,
    UNLOCK;

    static NextState nextState = new NextState();

    State next() {
        return nextState.next(this);
    }
}
