package com.example.domain.type.hour;

/**
 * 分(数)
 */
public class Minute {
    int time;

    Minute (int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("%d", time);
    } 
}
