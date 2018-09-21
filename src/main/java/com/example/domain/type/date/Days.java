package com.example.domain.type.date;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Days {

    long value;

    public Days(long value) {
        if (value < 0) throw new ArithmeticException();
        this.value = value;
    }

    public static Days between(LocalDate start, LocalDate end) {
        long betweenDays = ChronoUnit.DAYS.between(start, end);
        return new Days(betweenDays);
    }

    public Days add(Days other) {
        long result = Math.addExact(value, other.value);
        return new Days(result);
    }

    public Days subtract(Days other) {
        long result = Math.subtractExact(value, other.value);
        return new Days(result);
    }

    public boolean isEqualTo(Days other) {
        return value == other.value;
    }

    public boolean isGreaterThan(Days other) {
        return value > other.value;
    }

    public boolean isLessThan(Days other) {
        return value < other.value;
    }

    @Override
    public String toString() {
        return String.format("%s日", value);
    }
}
