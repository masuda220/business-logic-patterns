package com.example.domain.type.hour;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * 時刻を時分単位で表す
 */
public class ExtendedHourTime {

    int days;
    LocalTime time;

    public ExtendedHourTime(int days, LocalTime time) {
        this.days = days;
        this.time = time;
    }

    public long toMinutes() {
        return days * 24 * 60 + ChronoUnit.MINUTES.between(LocalTime.MIN, time);
    }

    static public ExtendedHourTime parse(String hourAndTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_TIME.withResolverStyle(ResolverStyle.LENIENT);
        TemporalAccessor parsed = formatter.parse(hourAndTime);

        int excessDays = parsed.query(DateTimeFormatter.parsedExcessDays()).getDays();
        LocalTime time = parsed.query(LocalTime::from);

        return new ExtendedHourTime(excessDays, time);
    }

    public String show() {
        return String.format("%02d:%02d", days * 24 + time.getHour(), time.getMinute());
    }

    @Override
    public String toString() {
        return "ExtendedHourTime{" +
                "days=" + days +
                ", time=" + time +
                '}';
    }
}
