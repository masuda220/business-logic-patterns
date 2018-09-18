package com.example.domain.type.hour;

/**
 * 分(数)
 */
public class Minute {
    int value;

    Minute (int time) {
        value = time;
    }

    Minute add(Minute minute) {
        value += minute.value;
        return new Minute(value);
    }

    @Override
    public String toString() {
        return String.format("%d", value);
    } 
}
