package com.example.domain.type.hour;

import java.time.LocalTime;

/**
 * 時刻を時分単位で表す
 */
public class HourTime {
    LocalTime hourPoint;

    HourTime (LocalTime hourPoint) {
        this.hourPoint = hourPoint;
    }

    HourTime (String hour, String minute) {
        hourPoint = LocalTime.parse(prepare(hour, minute));
    }

    private String prepare(String hour, String minute) {
        // FIXME 実装が美しくないので直したい
        String parseHour = zeroPadding(hour);
        String parseMinute = zeroPadding(minute);
        return parseHour + ":" + parseMinute;
    }

    private String zeroPadding(String target) {
        return String.format("%2s", target).replace(" ", "0");
    }

    @Override
    public String toString() {
        return String.format("HH:mm", hourPoint);
    } 
}
