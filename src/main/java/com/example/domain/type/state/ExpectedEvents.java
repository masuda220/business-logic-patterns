package com.example.domain.type.state;

import java.util.*;

import static com.example.domain.type.state.Event.*;
import static com.example.domain.type.state.State.*;

/**
 * 状態の期待するイベントを扱う
 */
class ExpectedEvents {

    Map<State, Set<Event>> expectedEvents;

    ExpectedEvents() {
        eventsWhen(OPENED, CLOSE);
        eventsWhen(CLOSED, OPEN, LOCK);
        eventsWhen(LOCKED, UNLOCK);
    }

    void eventsWhen(State state, Event... events) {
        if (expectedEvents == null) expectedEvents = new HashMap<>();
        expectedEvents.put(state, toEnumSet(events));
    }

    EnumSet<Event> toEnumSet(Event[] events) {
        return EnumSet.copyOf(Arrays.asList(events));
    }

    boolean isExpected(State state, Event event) {
        Set<Event> events = expectedEvents.get(state);
        return events.contains(event);
    }
}
