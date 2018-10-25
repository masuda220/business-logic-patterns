package com.example.domain.type.date;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * 日数
 */
public class Days {

    public static final Days Zero = new Days(0);

    int value;

    public Days(int value) {
        if (value < 0) throw new ArithmeticException();
        this.value = value;
    }

    public static Days between(LocalDate start, LocalDate end) {
        long betweenDays = ChronoUnit.DAYS.between(start, end);
        if (betweenDays > Integer.MAX_VALUE) throw new ArithmeticException();
        return new Days((int)betweenDays);
    }

    public Days add(Days other) {
        int result = Math.addExact(value, other.value);
        return new Days(result);
    }

    public Days subtract(Days other) {
        int result = Math.subtractExact(value, other.value);
        return new Days(result);
    }

    public Days multiply(int multiplicand) {
        int result = Math.multiplyExact(value, multiplicand);
        return new Days(result);
    }

    public int divide(Days divisor) {
        return value / divisor.value;
    }

    public Days remainder(Days divisor) {
        return new Days(value % divisor.value);
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

    public String show() {
        return String.format("%s日", value);
    }

    @Override
    public String toString() {
        return String.format("%s日", value);
    }
}
