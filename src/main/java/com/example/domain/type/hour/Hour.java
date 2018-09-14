package com.example.domain.type.hour;

/**
 * 時間(数)
 */
public class Hour {
    int time;

    Hour (int time) {
        this.time = time;
    }

    Minute toMinute() {
        return new Minute(time * 60);
    }

    @Override
    public String toString() {
        return String.format("%d", time);
    } 
}
