package com.example.domain.type.date;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class DateRangeTest {

    @Test
    @DisplayName("開始終了のファクトリメソッド")
    void fromTo() {
        LocalDate date20180901 = LocalDate.of(2018, 9, 1);
        LocalDate date20180902 = LocalDate.of(2018, 9, 2);
        DateRange actual = DateRange.fromTo(date20180901, date20180902);
        assertEquals(date20180901, actual.start);
        assertEquals(date20180902, actual.end);
    }

    @Test
    @DisplayName("開始終了のファクトリメソッド 同値")
    void fromToBySameDate() {
        LocalDate start20180901 = LocalDate.of(2018, 9, 1);
        LocalDate end20180901 = LocalDate.of(2018, 9, 1);
        DateRange actual = DateRange.fromTo(start20180901, end20180901);
        assertEquals(start20180901, actual.start);
        assertEquals(end20180901, actual.end);
    }

    @Test
    @DisplayName("開始終了のファクトリメソッド 開始＞終了で例外")
    void fromToIllegalArgument() {
        LocalDate date20180902 = LocalDate.of(2018, 9, 2);
        LocalDate date20180901 = LocalDate.of(2018, 9, 1);
        assertThrows(IllegalArgumentException.class, () -> DateRange.fromTo(date20180902, date20180901));
    }

    @Test
    @DisplayName("昨日から今日までのファクトリメソッド")
    void from() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDate today = LocalDate.now();
        DateRange actual = DateRange.from(yesterday);
        assertEquals(yesterday, actual.start);
        assertEquals(today, actual.end);
    }

    @Test
    @DisplayName("開始（今日）から今日のファクトリメソッド 同値")
    void fromByToday() {
        LocalDate today = LocalDate.now();
        DateRange actual = DateRange.from(today);
        assertEquals(today, actual.start);
        assertEquals(today, actual.end);
    }

    @Test
    @DisplayName("明日から今日のファクトリメソッド 開始＞終了で例外")
    void fromIllegalArgument() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        assertThrows(IllegalArgumentException.class, () -> DateRange.from(tomorrow));
    }

    @Test
    @DisplayName("今日から明日までのファクトリメソッド")
    void to() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        DateRange actual = DateRange.to(tomorrow);
        assertEquals(today, actual.start);
        assertEquals(tomorrow, actual.end);
    }

    @Test
    @DisplayName("今日から終了（今日）のファクトリメソッド 同値")
    void toByToday() {
        LocalDate today = LocalDate.now();
        DateRange actual = DateRange.to(today);
        assertEquals(today, actual.start);
        assertEquals(today, actual.end);
    }

    @Test
    @DisplayName("今日から昨日のファクトリメソッド 開始＞終了で例外")
    void toIllegalArgument() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        assertThrows(IllegalArgumentException.class, () -> DateRange.to(yesterday));
    }

    @Test
    @DisplayName("期間内の判定")
    void isContains() {
        LocalDate date20180830 = LocalDate.of(2018, 8, 30);
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate other20180831 = LocalDate.of(2018, 8, 31);
        LocalDate date20180901 = LocalDate.of(2018, 9, 1);
        LocalDate date20180902 = LocalDate.of(2018, 9, 2);
        LocalDate other20180902 = LocalDate.of(2018, 9, 2);
        LocalDate date20180903 = LocalDate.of(2018, 9, 3);
        DateRange range0831to0902 = DateRange.fromTo(date20180831, date20180902);

        assertAll(
                () -> assertFalse(range0831to0902.isContains(date20180830), "期間前"),
                () -> assertTrue(range0831to0902.isContains(other20180831), "期間開始日"),
                () -> assertTrue(range0831to0902.isContains(date20180901), "期間中"),
                () -> assertTrue(range0831to0902.isContains(other20180902), "期間終了日"),
                () -> assertFalse(range0831to0902.isContains(date20180903), "期間後")
        );
    }

    @Test
    @DisplayName("期間前の判定")
    void beforeStart() {
        LocalDate date20180830 = LocalDate.of(2018, 8, 30);
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate other20180831 = LocalDate.of(2018, 8, 31);
        LocalDate date20180901 = LocalDate.of(2018, 9, 1);
        LocalDate date20180902 = LocalDate.of(2018, 9, 2);
        LocalDate other20180902 = LocalDate.of(2018, 9, 2);
        LocalDate date20180903 = LocalDate.of(2018, 9, 3);
        DateRange range0831to0902 = DateRange.fromTo(date20180831, date20180902);

        assertAll(
                () -> assertTrue(range0831to0902.isBeforeStart(date20180830), "期間前"),
                () -> assertFalse(range0831to0902.isBeforeStart(other20180831), "期間開始日"),
                () -> assertFalse(range0831to0902.isBeforeStart(date20180901), "期間中"),
                () -> assertFalse(range0831to0902.isBeforeStart(other20180902), "期間終了日"),
                () -> assertFalse(range0831to0902.isBeforeStart(date20180903), "期間後")
        );
    }

    @Test
    @DisplayName("期間後の判定")
    void afterEnd() {
        LocalDate date20180830 = LocalDate.of(2018, 8, 30);
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate other20180831 = LocalDate.of(2018, 8, 31);
        LocalDate date20180901 = LocalDate.of(2018, 9, 1);
        LocalDate date20180902 = LocalDate.of(2018, 9, 2);
        LocalDate other20180902 = LocalDate.of(2018, 9, 2);
        LocalDate date20180903 = LocalDate.of(2018, 9, 3);
        DateRange range0831to0902 = DateRange.fromTo(date20180831, date20180902);

        assertAll(
                () -> assertFalse(range0831to0902.isAfterEnd(date20180830), "期間前"),
                () -> assertFalse(range0831to0902.isAfterEnd(other20180831), "期間開始日"),
                () -> assertFalse(range0831to0902.isAfterEnd(date20180901), "期間中"),
                () -> assertFalse(range0831to0902.isAfterEnd(other20180902), "期間終了日"),
                () -> assertTrue(range0831to0902.isAfterEnd(date20180903), "期間後")
        );
    }
}
