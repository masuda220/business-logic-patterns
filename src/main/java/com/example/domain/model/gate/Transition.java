package com.example.domain.model.gate;

/**
 * 遷移前、遷移後の状態とイベントの組み合わせを扱う
 */
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

    boolean matches(State from) {
        return this.from == from;
    }

    State next() {
        return to;
    }
}
