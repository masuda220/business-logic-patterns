package com.example.domain.type.date;

/**
 * 日数を週単位で表現する
 */
public class Weeks {

    static final Days DAYS_OF_WEEKS = new Days(7);

    Days days;

    public Weeks(Days days) {
        this.days = days;
    }

    public Weeks add(Days otherDays) {
        return new Weeks(days.add(otherDays));
    }

    public Weeks add(Weeks other) {
        return add(other.days);
    }

    public Weeks subtract(Days otherDays) {
        return new Weeks(days.subtract(otherDays));
    }

    public Weeks subtract(Weeks other) {
        return subtract(other.days);
    }

    public boolean isEqualTo(Weeks other) {
        return days.isEqualTo(other.days);
    }

    public boolean isGreaterThan(Weeks other) {
        return days.isGreaterThan(other.days);
    }

    public boolean isLessThan(Weeks other) {
        return days.isLessThan(other.days);
    }

    public String show() {
        int weeks = days.divide(DAYS_OF_WEEKS);
        Days remainder = days.remainder(DAYS_OF_WEEKS);

        if (remainder.isEqualTo(Days.Zero))
            return String.format("%s週間", weeks);

        return String.format("%s週間と%s", weeks, remainder.show());
    }

    @Override
    public String toString() {
        int weeks = days.divide(DAYS_OF_WEEKS);
        Days remainder = days.remainder(DAYS_OF_WEEKS);

        if (remainder.isEqualTo(Days.Zero))
            return String.format("%sweeks", weeks);

        return String.format("%sweeks%sdays", weeks, remainder.value);
    }
}
