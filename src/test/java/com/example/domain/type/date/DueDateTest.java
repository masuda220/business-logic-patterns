package com.example.domain.type.date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DueDateTest {

    @Test
    @DisplayName("期限前判定 期日前")
    public void beforeDueBefore() {
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate date20180901 = LocalDate.of(2018, 9, 1);
        DueDate due20180901 = DueDate.of(date20180901);
        boolean actual = due20180901.isBeforeDue(date20180831);
        assertTrue(actual);
    }

    @Test
    @DisplayName("期限前判定 期日当日")
    public void beforeDueToday() {
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate other20180831 = LocalDate.of(2018, 8, 31);
        DueDate due20180831 = DueDate.of(date20180831);
        boolean actual = due20180831.isBeforeDue(other20180831);
        assertFalse(actual);
    }

    @Test
    @DisplayName("期限前判定 期日後")
    public void beforeDueAfter() {
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate date20180901 = LocalDate.of(2018, 9, 1);
        DueDate due20180831 = DueDate.of(date20180831);
        boolean actual = due20180831.isBeforeDue(date20180901);
        assertFalse(actual);
    }

    @Test
    @DisplayName("期限当日判定 期日前")
    public void dueDateBefore() {
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate date20180901 = LocalDate.of(2018, 9, 1);
        DueDate due20180901 = DueDate.of(date20180901);
        boolean actual = due20180901.isDueDate(date20180831);
        assertFalse(actual);
    }

    @Test
    @DisplayName("期限当日判定 期日当日")
    public void dueDateToday() {
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate other20180831 = LocalDate.of(2018, 8, 31);
        DueDate due20180831 = DueDate.of(date20180831);
        boolean actual = due20180831.isDueDate(other20180831);
        assertTrue(actual);
    }

    @Test
    @DisplayName("期限当日判定 期日後")
    public void dueDateAfter() {
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate date20180901 = LocalDate.of(2018, 9, 1);
        DueDate due20180831 = DueDate.of(date20180831);
        boolean actual = due20180831.isDueDate(date20180901);
        assertFalse(actual);
    }

    @Test
    @DisplayName("期限切れ判定 期日前")
    public void overDueBefore() {
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate date20180901 = LocalDate.of(2018, 9, 1);
        DueDate due20180901 = DueDate.of(date20180901);
        boolean actual = due20180901.isOverDue(date20180831);
        assertFalse(actual);
    }

    @Test
    @DisplayName("期限切れ判定 期日当日")
    public void overDueToday() {
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate other20180831 = LocalDate.of(2018, 8, 31);
        DueDate due20180831 = DueDate.of(date20180831);
        boolean actual = due20180831.isOverDue(other20180831);
        assertFalse(actual);
    }

    @Test
    @DisplayName("期限切れ判定 期日後")
    public void overDueAfter() {
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate date20180901 = LocalDate.of(2018, 9, 1);
        DueDate due20180831 = DueDate.of(date20180831);
        boolean actual = due20180831.isOverDue(date20180901);
        assertTrue(actual);
    }

    @Test
    @DisplayName("期日までの日数")
    public void remainingDays() {
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate date20190901 = LocalDate.of(2019, 9, 1);
        DueDate due20190901 = DueDate.of(date20190901);
        Days actual = due20190901.remainingDays(date20180831);
        assertEquals(366, actual.value, String.format("%s - %s", due20190901, date20180831));
    }

    @Test
    @DisplayName("期日までの日数 当日")
    public void remainingDaysCurrentDay() {
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate other20180831 = LocalDate.of(2018, 8, 31);
        DueDate due20180831 = DueDate.of(date20180831);
        Days actual = due20180831.remainingDays(other20180831);
        assertEquals(0, actual.value);
    }

    @Test
    @DisplayName("期日までの日数 マイナス")
    public void remainingDaysOverflow() {
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate date20180901 = LocalDate.of(2018, 9, 1);
        DueDate due20180831 = DueDate.of(date20180831);
        assertThrows(IllegalArgumentException.class, () -> due20180831.remainingDays(date20180901));
    }

    @Test
    @DisplayName("超過日数")
    public void daysPast() {
        LocalDate date20170831 = LocalDate.of(2017, 8, 31);
        LocalDate date20180901 = LocalDate.of(2018, 9, 1);
        DueDate due20170831 = DueDate.of(date20170831);
        Days actual = due20170831.daysPast(date20180901);
        assertEquals(366, actual.value, String.format("%s - %s", date20180901, due20170831));
    }

    @Test
    @DisplayName("超過日数 当日")
    public void daysPastCurrentDay() {
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate other20180831 = LocalDate.of(2018, 8, 31);
        DueDate due20180831 = DueDate.of(date20180831);
        Days actual = due20180831.daysPast(other20180831);
        assertEquals(0, actual.value);
    }

    @Test
    @DisplayName("超過日数 マイナス")
    public void daysPastOverflow() {
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate date20180901 = LocalDate.of(2018, 9, 1);
        DueDate due20180901 = DueDate.of(date20180901);
        assertThrows(IllegalArgumentException.class, () -> due20180901.daysPast(date20180831));
    }
}