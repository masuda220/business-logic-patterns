package com.example.domain.type.date;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class DateRangeTest {

    @Test
    @DisplayName("開始終了のファクトリメソッド")
    public void fromTo() {
        LocalDate date20180901 = LocalDate.of(2018, 9, 1);
        LocalDate date20180902 = LocalDate.of(2018, 9, 2);
        DateRange actual = DateRange.fromTo(date20180901, date20180902);
        assertEquals(date20180901, actual.start);
        assertEquals(date20180902, actual.end);
    }

    @Test
    @DisplayName("開始終了のファクトリメソッド 同値")
    public void fromToBySameDate() {
        LocalDate start20180901 = LocalDate.of(2018, 9, 1);
        LocalDate end20180901 = LocalDate.of(2018, 9, 1);
        DateRange actual = DateRange.fromTo(start20180901, end20180901);
        assertEquals(start20180901, actual.start);
        assertEquals(end20180901, actual.end);
    }

    @Test
    @DisplayName("開始終了のファクトリメソッド 開始＞終了で例外")
    public void fromToIllegalArgument() {
        LocalDate date20180902 = LocalDate.of(2018, 9, 2);
        LocalDate date20180901 = LocalDate.of(2018, 9, 1);
        assertThrows(IllegalArgumentException.class, () -> DateRange.fromTo(date20180902, date20180901));
    }

    @Test
    @DisplayName("昨日から今日までのファクトリメソッド")
    public void from() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDate today = LocalDate.now();
        DateRange actual = DateRange.from(yesterday);
        assertEquals(yesterday, actual.start);
        assertEquals(today, actual.end);
    }

    @Test
    @DisplayName("開始（今日）から今日のファクトリメソッド 同値")
    public void fromByToday() {
        LocalDate today = LocalDate.now();
        DateRange actual = DateRange.from(today);
        assertEquals(today, actual.start);
        assertEquals(today, actual.end);
    }

    @Test
    @DisplayName("明日から今日のファクトリメソッド 開始＞終了で例外")
    public void fromIllegalArgument() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        assertThrows(IllegalArgumentException.class, () -> DateRange.from(tomorrow));
    }

    @Test
    @DisplayName("今日から明日までのファクトリメソッド")
    public void to() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        DateRange actual = DateRange.to(tomorrow);
        assertEquals(today, actual.start);
        assertEquals(tomorrow, actual.end);
    }

    @Test
    @DisplayName("今日から終了（今日）のファクトリメソッド 同値")
    public void toByToday() {
        LocalDate today = LocalDate.now();
        DateRange actual = DateRange.to(today);
        assertEquals(today, actual.start);
        assertEquals(today, actual.end);
    }

    @Test
    @DisplayName("今日から昨日のファクトリメソッド 開始＞終了で例外")
    public void toIllegalArgument() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        assertThrows(IllegalArgumentException.class, () -> DateRange.to(yesterday));
    }

    @Test
    @DisplayName("期間の判定 開始前日")
    public void containsByDayBeforeOfStart() {
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate date20180901 = LocalDate.of(2018, 9, 1);
        LocalDate date20180902 = LocalDate.of(2018, 9, 2);
        DateRange range20180901to20180902 = DateRange.fromTo(date20180901, date20180902);
        DateRange.ContainType actual = range20180901to20180902.contains(date20180831);
        assertEquals(DateRange.ContainType.期間前, actual);
    }

    @Test
    @DisplayName("期間の判定 終了翌日")
    public void containsByNextDayOfEnd() {
        LocalDate date20180830 = LocalDate.of(2018, 8, 30);
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate date20180901 = LocalDate.of(2018, 9, 1);
        DateRange range20180830to20180831 = DateRange.fromTo(date20180830, date20180831);
        DateRange.ContainType actual = range20180830to20180831.contains(date20180901);
        assertEquals(DateRange.ContainType.期間後, actual);
    }

    @Test
    @DisplayName("期間の判定 開始当日")
    public void containsBySameDayOfStart() {
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate date20180901 = LocalDate.of(2018, 9, 1);
        LocalDate otherDate20180831 = LocalDate.of(2018, 8, 31);
        DateRange range20180831to20180901 = DateRange.fromTo(date20180831, date20180901);
        DateRange.ContainType actual = range20180831to20180901.contains(otherDate20180831);
        assertEquals(DateRange.ContainType.期間内, actual);
    }

    @Test
    @DisplayName("期間の判定 終了当日")
    public void containsBySameDayOfEnd() {
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate date20180901 = LocalDate.of(2018, 9, 1);
        LocalDate otherDate20180901 = LocalDate.of(2018, 9, 1);
        DateRange range20180831to20180901 = DateRange.fromTo(date20180831, date20180901);
        DateRange.ContainType actual = range20180831to20180901.contains(otherDate20180901);
        assertEquals(DateRange.ContainType.期間内, actual);
    }

    @Test
    @DisplayName("期間の判定 開始＝終了＝判定日")
    public void containsBySameDayOfStartAndEnd() {
        LocalDate date20180901 = LocalDate.of(2018, 9, 1);
        LocalDate otherDate20180901 = LocalDate.of(2018, 9, 1);
        DateRange range20180901to20180901 = DateRange.fromTo(date20180901, date20180901);
        DateRange.ContainType actual = range20180901to20180901.contains(otherDate20180901);
        assertEquals(DateRange.ContainType.期間内, actual);
    }
}
