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
        value = LocalTime.parse(prepare(hour, minute));
    }

    private String prepare(int hour, int minute) {
        // FIXME LocalTimeはxx:xx形式でなければParseできないのでintで1桁の場合を考慮する
        String parseHour = zeroPadding(hour);
        String parseMinute = zeroPadding(minute);
        return parseHour + ":" + parseMinute;
    }

    private String zeroPadding(int target) {
        return String.format("%02d", target);
    }

    @Override
    public String toString() {
        return String.format("HH:mm", value);
    } 
}
