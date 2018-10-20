package com.example.domain.type.date;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Days {

    public static final Days Zero = new Days(0);

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

    public Days multiply(long multiplicand) {
        long result = Math.multiplyExact(value, multiplicand);
        return new Days(result);
    }

    public Days[] divideAndRemainder(Days divisor) {
        Days[] result = {
                divide(divisor),
                remainder(divisor)
        };
        return result;
    }

    Days divide(Days divisor) {
        return new Days(value / divisor.value);
    }

    Days remainder(Days divisor) {
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
