package com.example.domain.type.state;

public class Transition {
    State from;
    State to;
    Event event;

    Transition(State from, State to, Event event) {
        this.from = from;
        this.to = to;
        this.event = event;
    }

    boolean matches(State from, Event event) {
        if (this.from != from) return false;
        return this.event == event;
    }

    State next() {
        return to;
    }
}
