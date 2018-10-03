package com.example.domain.type.state;

public enum Event {
    OPEN {
        @Override
        public State next() {
            return State.OPENED;
        }
    },
    CLOSE{
        @Override
        public State next() {
            return State.CLOSED;
        }
    },
    LOCK{
        @Override
        public State next() {
            return State.LOCKED;
        }
    };

    public abstract State next();
}
