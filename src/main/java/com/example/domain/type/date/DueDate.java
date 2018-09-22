package com.example.domain.type.date;

import java.time.LocalDate;

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

    public boolean isBeforeDue(LocalDate date) {
        return date.isBefore(value);
    }

    public boolean isDueDate(LocalDate date) {
        return date.equals(value);
    }

    public boolean isOverDue(LocalDate date) {
        return date.isAfter(value);
    }

    public Days remainingDays(LocalDate date) {
        if (isOverDue(date)) throw new IllegalArgumentException();
        return Days.between(date, value);
    }

    public Days daysPast(LocalDate date) {
        if (isBeforeDue(date)) throw new IllegalArgumentException();
        return Days.between(value, date);
    }

    @Override
    public String toString() {
        return String.format("%s", value);
    }
}
