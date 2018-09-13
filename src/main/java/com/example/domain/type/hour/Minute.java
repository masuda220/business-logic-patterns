package com.example.domain.type.hour;

/**
 * 分(数)
 */
public class Minute {
    int time;

    Minute (int time) {
        this.time = time;
    }

    Minute add(Minute minute) {
        time += minute.time;
        return new Minute(time);
    }

    @Override
    public String toString() {
        return String.format("%d", time);
    } 
}
