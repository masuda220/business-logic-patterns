package com.example.domain.type.hour;

import java.time.LocalTime;

/**
 * 時刻を時分単位で表す
 */
public class HourTime {
    LocalTime value;

    public HourTime (int hour, int minute) {
        value = LocalTime.of(hour, minute);
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", value.getHour(), value.getMinute());
    }
}
