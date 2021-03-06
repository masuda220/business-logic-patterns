package com.example.domain.model.gate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.domain.model.gate.Event.*;
import static com.example.domain.model.gate.State.*;

/**
 * 状態遷移を扱う
 */
public class Transitions {
    List<Transition> transitions;

    Transitions() {
        addTransition(OPENED, CLOSED, CLOSE);
        addTransition(CLOSED, OPENED, OPEN);
        addTransition(CLOSED, LOCKED, LOCK);
        addTransition(LOCKED, CLOSED, UNLOCK);
    }

    void addTransition(State from, State to, Event event) {
        if (transitions == null) transitions = new ArrayList<>();
        transitions.add(new Transition(from, to, event));
    }

    State next(State from, Event event) {
        for(Transition transition : transitions) {
            if (transition.matches(from, event)) return transition.next();
        }
        throw new IllegalArgumentException();
    }

    boolean isExpected(State from, Event event) {
        return expectedEventsOf(from).contains(event);
    }

    Event[] expectedEvents(State from) {
        return expectedEventsOf(from).toArray(new Event[0]);
    }

    List<Event> expectedEventsOf(State from) {
        return transitions
                .stream()
                .filter(transition -> transition.matches(from))
                .map(transition -> transition.event)
                .collect(Collectors.toList());
    }
}
