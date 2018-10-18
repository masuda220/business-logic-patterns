package com.example.domain.type.date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeeksTest {

    @Test
    @DisplayName("Daysの加算")
    void addDays() {
        Weeks oneDay = new Weeks(new Days(1));
        Days tenDays = new Days(10);
        long expected = 11;
        Weeks actual = oneDay.add(tenDays);
        assertEquals(expected, actual.days.value);
    }

    @Test
    @DisplayName("Weeksの加算")
    void addWeeks() {
        Weeks oneDay = new Weeks(new Days(1));
        Weeks tenDays = new Weeks(new Days(10));
        long expected = 11;
        Weeks actual = oneDay.add(tenDays);
        assertEquals(expected, actual.days.value);
    }

    @Test
    @DisplayName("Daysの減算")
    void subtractDays() {
        Weeks tenDays = new Weeks(new Days(10));
        Days oneDay = new Days(1);
        long expected = 9;
        Weeks actual = tenDays.subtract(oneDay);
        assertEquals(expected, actual.days.value);
    }

    @Test
    @DisplayName("Weeksの減算")
    void subtractWeeks() {
        Weeks tenDays = new Weeks(new Days(10));
        Weeks oneDay = new Weeks(new Days(1));
        long expected = 9;
        Weeks actual = tenDays.subtract(oneDay);
        assertEquals(expected, actual.days.value);
    }

    @Test
    @DisplayName("イコール")
    void isEqualTo() {
        Weeks one = new Weeks(new Days(1));
        Weeks otherOne = new Weeks(new Days(1));
        Weeks two = new Weeks(new Days(2));
        assertTrue(one.isEqualTo(otherOne), "同値");
        assertFalse(one.isEqualTo(two), "異なる値");
    }

    @Test
    @DisplayName("大なり")
    void isLessOrEqualTo() {
        Weeks one = new Weeks(new Days(1));
        Weeks two = new Weeks(new Days(2));
        Weeks three = new Weeks(new Days(3));
        Weeks otherTwo = new Weeks(new Days(2));
        assertTrue(two.isGreaterThan(one), "2 は 1 より大きい");
        assertFalse(two.isGreaterThan(otherTwo), "2 は 2 より大きくない");
        assertFalse(two.isGreaterThan(three), "2 は 3 より大きくない");
    }

    @Test
    @DisplayName("小なり")
    void isLessThan() {
        Weeks one = new Weeks(new Days(1));
        Weeks two = new Weeks(new Days(2));
        Weeks three = new Weeks(new Days(3));
        Weeks otherTwo = new Weeks(new Days(2));
        assertFalse(two.isLessThan(one), "2 は 1 より小さくない");
        assertFalse(two.isLessThan(otherTwo), "2 は 2 より小さくない");
        assertTrue(two.isLessThan(three), "2 は 3 より小さい");
    }

    @Test
    @DisplayName("文字列表現")
    void show() {
        Weeks oneDay = new Weeks(new Days(1));
        assertEquals("0週間と1日", oneDay.show());
        Weeks tenDays = new Weeks(new Days(10));
        assertEquals("1週間と3日", tenDays.show());
        Weeks twoWeeks = new Weeks(new Days(14));
        assertEquals("2週間", twoWeeks.show());
    }

    @Test
    @DisplayName("toString")
    void string() {
        Weeks oneDay = new Weeks(new Days(1));
        assertEquals("0weeks1days", oneDay.toString());
        Weeks tenDays = new Weeks(new Days(10));
        assertEquals("1weeks3days", tenDays.toString());
        Weeks twoWeeks = new Weeks(new Days(14));
        assertEquals("2weeks", twoWeeks.toString());
    }
}
