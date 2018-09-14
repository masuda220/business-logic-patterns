package com.example.domain.type.hour;

/**
 * x時間y分
 */
public class HourAndMinute {
    Hour hour;
    Minute minute;

    HourAndMinute (Hour hour, Minute minute) {
        this.hour = hour;
        this.minute = minute;
    }

    static HourAndMinute from(Minute minute) {
        Hour quotient = new Hour(minute.time / 60);
        Minute remainder = new Minute(minute.time % 60);
        return new HourAndMinute(quotient, remainder);
    }

    Minute toMinute() {
        return minute.add(hour.toMinute());
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
        return prepare(hour.toString(), minute.toString());
    } 
}
