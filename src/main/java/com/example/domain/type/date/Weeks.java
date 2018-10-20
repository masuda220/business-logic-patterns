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
        Days[] weeksAndDays = days.divideAndRemainder(DAYS_OF_WEEKS);

        if (weeksAndDays[1].isEqualTo(Days.Zero))
            return String.format("%s週間", weeksAndDays[0].value);

        return String.format("%s週間と%s", weeksAndDays[0].value, weeksAndDays[1].show());
    }

    @Override
    public String toString() {
        Days[] weeksAndDays = days.divideAndRemainder(DAYS_OF_WEEKS);

        if (weeksAndDays[1].isEqualTo(Days.Zero))
            return String.format("%sweeks", weeksAndDays[0].value);

        return String.format("%sweeks%sdays", weeksAndDays[0].value, weeksAndDays[1].value);
    }
}
