package com.example.domain.type.date;

import java.time.LocalDate;

/**
 * 期間
 */
public class DateRange {

    public enum ContainType {
        期間内,
        期間前,
        期間後
    }
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

    public ContainType contains(LocalDate target) {
        if (start.isEqual(target)) return ContainType.期間内;
        if (end.isEqual(target)) return ContainType.期間内;
        if (start.isAfter(target)) return ContainType.期間前;
        return ContainType.期間後;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", start, end);
    }
}
