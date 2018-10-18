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
        return toString("%s週間", "と%s日");
    }

    @Override
    public String toString() {
        return toString("%sweeks", "%sdays");
    }

    Days numberOfWeek() {
        return new Days(days.value / DAYS_OF_WEEKS.value);
    }

    Days remainderOfWeek() {
        return new Days(days.value % DAYS_OF_WEEKS.value);
    }

    String toString(String formatOfWeeks, String formatOfDays) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(toStringWeeks(formatOfWeeks));
        stringBuilder.append(toStringDays(formatOfDays));
        return stringBuilder.toString();
    }

    String toStringWeeks(String format) {
        Days numberOfWeek = numberOfWeek();
        return String.format(format, numberOfWeek.value);
    }

    String toStringDays(String format) {
        Days remainderOfWeek = remainderOfWeek();
        if (remainderOfWeek.isEqualTo(Days.Zero)) return "";
        return String.format(format,remainderOfWeek.value);
    }
}
