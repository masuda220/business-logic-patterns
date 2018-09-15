package com.example.domain.type.date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DueDateTest {

    @Test
    @DisplayName("期日後")
    public void overDue() {
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate date20180901 = LocalDate.of(2018, 9, 1);
        DueDate due20180831 = DueDate.of(date20180831);
        boolean actual = due20180831.isOverDue(date20180901);
        assertTrue(actual);
    }

    @Test
    @DisplayName("期日当日")
    public void dueToday() {
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate other20180831 = LocalDate.of(2018, 8, 31);
        DueDate due20180831 = DueDate.of(date20180831);
        boolean actual = due20180831.isOverDue(other20180831);
        assertFalse(actual);
    }

    @Test
    @DisplayName("期日前")
    public void beforeDue() {
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate date20180901 = LocalDate.of(2018, 9, 1);
        DueDate due20180901 = DueDate.of(date20180901);
        boolean actual = due20180901.isOverDue(date20180831);
        assertFalse(actual);
    }

    @Test
    @DisplayName("期日までの日数")
    public void remainingDays() {
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate date20190901 = LocalDate.of(2019, 9, 1);
        DueDate due20190901 = DueDate.of(date20190901);
        int actual = due20190901.remainingDays(date20180831);
        assertEquals(366, actual, String.format("%s - %s", due20190901, date20180831));
    }

    @Test
    @DisplayName("期日までの日数 当日")
    public void remainingDaysCurrentDay() {
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate other20180831 = LocalDate.of(2018, 8, 31);
        DueDate due20180831 = DueDate.of(date20180831);
        int actual = due20180831.remainingDays(other20180831);
        assertEquals(0, actual);
    }

    @Test
    @DisplayName("期日までの日数 オーバーフロー")
    public void remainingDaysOverflow() {
        LocalDate dateMax = LocalDate.MAX;
        LocalDate dateMin = LocalDate.MIN;
        DueDate dueMax = DueDate.of(dateMax);
        assertThrows(ArithmeticException.class, () -> dueMax.remainingDays(dateMin));
    }

    @Test
    @DisplayName("超過日数")
    public void daysPast() {
        LocalDate date20170831 = LocalDate.of(2017, 8, 31);
        LocalDate date20180901 = LocalDate.of(2018, 9, 1);
        DueDate due20170831 = DueDate.of(date20170831);
        int actual = due20170831.daysPast(date20180901);
        assertEquals(366, actual, String.format("%s - %s", date20180901, due20170831));
    }

    @Test
    @DisplayName("超過日数 当日")
    public void daysPastCurrentDay() {
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate other20180831 = LocalDate.of(2018, 8, 31);
        DueDate due20180831 = DueDate.of(date20180831);
        int actual = due20180831.daysPast(other20180831);
        assertEquals(0, actual);
    }

    @Test
    @DisplayName("超過日数 オーバーフロー")
    public void daysPastOverflow() {
        LocalDate dateMax = LocalDate.MAX;
        LocalDate dateMin = LocalDate.MIN;
        DueDate dueMin = DueDate.of(dateMin);
        assertThrows(ArithmeticException.class, () -> dueMin.daysPast(dateMax));
    }
}