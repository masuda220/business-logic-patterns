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

    Minute toMinute() {
        return new Minute(hour.toMinute().time + minute.time);
    }

    @Override
    public String toString() {
        return hour.toString() + ':' + minute.toString();
    } 
}
