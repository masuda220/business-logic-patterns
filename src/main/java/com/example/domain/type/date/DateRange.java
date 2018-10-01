package com.example.domain.type.date;

import java.time.LocalDate;

/**
 * 期間
 */
public class DateRange {

    LocalDate start;
    LocalDate end;

    private DateRange(LocalDate start, LocalDate end) {
        if (end.isBefore(start)) throw new IllegalArgumentException();
        this.start = start;
        this.end = end;
    }

    public static DateRange fromTo(LocalDate start, LocalDate end) {
        return new DateRange(start, end);
    }

    public static DateRange from(LocalDate start) {
        return new DateRange(start, LocalDate.now());
    }

    public static DateRange to(LocalDate end) {
        return new DateRange(LocalDate.now(), end);
    }

    public boolean isContains(LocalDate date) {
        if (start.isAfter(date)) return false;
        if (end.isBefore(date)) return false;
        return true;
    }
    public boolean isBeforeStart(LocalDate date) {
        return date.isBefore(start);
    }
    public boolean isAfterEnd(LocalDate date) {
        return date.isAfter(end);
    }

    @Override
    public String toString() {
        return String.format("%s - %s", start, end);
    }
}
