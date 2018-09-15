package com.example.domain.type.date;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * 期日
 */
public class DueDate {

    LocalDate value;

    private DueDate(LocalDate value) {
        this.value = value;
    }

    public static DueDate of(LocalDate date) {
        return new DueDate(date);
    }

    public boolean isOverDue(LocalDate date) {
        return date.isAfter(value);
    }

    public int remainingDays(LocalDate date) {
        if (isOverDue(date)) throw new IllegalArgumentException();
        long remainingDays = ChronoUnit.DAYS.between(date, value);
        // TODO : 実際に使用される日付の範囲を考えるとこのチェックは不要？
        if (remainingDays > (long) Integer.MAX_VALUE) throw new ArithmeticException();
        return (int) remainingDays;
    }

    public int daysPast(LocalDate date) {
        if (value.isAfter(date)) throw new IllegalArgumentException();
        long daysPast = ChronoUnit.DAYS.between(value, date);
        // TODO : 実際に使用される日付の範囲を考えるとこのチェックは不要？
        if (daysPast > (long) Integer.MAX_VALUE) throw new ArithmeticException();
        return (int) daysPast;
    }

    @Override
    public String toString() {
        return String.format("%s", value);
    }
}
