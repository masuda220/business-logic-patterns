package com.example.domain.type.hour;

import java.time.LocalTime;

/**
 * 時刻を時分単位で表す
 */
public class HourTime {
    LocalTime value;

    HourTime (LocalTime hourPoint) {
        this.value = hourPoint;
    }

    HourTime (int hour, int minute) {
        value = LocalTime.of(hour, minute);
    }

    @Override
    public String toString() {
        return String.format("HH:mm", value);
    } 
}
