package com.example.domain.type.date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DaysTest {

    @Test
    @DisplayName("開始終了から期間の日数を生成")
    void between() {
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate date20180901 = LocalDate.of(2018, 9, 1);
        long expected = 1;
        Days actual = Days.between(date20180831, date20180901);
        assertEquals(expected, actual.value);
    }

    @Test
    @DisplayName("開始終了が逆でから日数生成で例外")
    void betweenFailed() {
        LocalDate date20180831 = LocalDate.of(2018, 8, 31);
        LocalDate date20180901 = LocalDate.of(2018, 9, 1);
        assertThrows(ArithmeticException.class, () -> Days.between(date20180901, date20180831));
    }

    @Test
    @DisplayName("日数0を生成")
    void constructorZero() {
        long expected = 0;
        Days actual = new Days(0);
        assertEquals(expected, actual.value);
    }

    @Test
    @DisplayName("コンストラクタにマイナスで例外")
    void constructorFailed() {
        assertThrows(ArithmeticException.class, () -> new Days(-1));
    }

    @Test
    @DisplayName("加算")
    void add() {
        Days one = new Days(1);
        Days two = new Days(2);
        long expected = 3;
        Days actual = one.add(two);
        assertEquals(expected, actual.value);
    }

    @Test
    @DisplayName("加算オーバーフロー")
    void addOverflow() {
        Days max = new Days(Long.MAX_VALUE);
        Days one = new Days(1);
        assertThrows(ArithmeticException.class, () -> max.add(one));
    }

    @Test
    @DisplayName("減算")
    void subtract() {
        Days one = new Days(1);
        Days two = new Days(2);
        long expected = 1;
        Days actual = two.subtract(one);
        assertEquals(expected, actual.value);
    }

    @Test
    @DisplayName("マイナスになる減算で例外")
    void subtractMinus() {
        Days one = new Days(1);
        Days two = new Days(2);
        assertThrows(ArithmeticException.class, () -> one.subtract(two));
    }

    @Test
    @DisplayName("イコール")
    void isEqualTo() {
        Days one = new Days(1);
        Days otherOne = new Days(1);
        Days two = new Days(2);
        assertTrue(one.isEqualTo(otherOne), "同値");
        assertFalse(one.isEqualTo(two), "異なる値");
    }

    @Test
    @DisplayName("大なり")
    void isLessOrEqualTo() {
        Days one = new Days(1);
        Days two = new Days(2);
        Days three = new Days(3);
        Days otherTwo = new Days(2);
        assertTrue(two.isGreaterThan(one), "2 は 1 より大きい");
        assertFalse(two.isGreaterThan(otherTwo), "2 は 2 より大きくない");
        assertFalse(two.isGreaterThan(three), "2 は 3 より大きくない");
    }

    @Test
    @DisplayName("小なり")
    void isLessThan() {
        Days one = new Days(1);
        Days two = new Days(2);
        Days three = new Days(3);
        Days otherTwo = new Days(2);
        assertFalse(two.isLessThan(one), "2 は 1 より小さくない");
        assertFalse(two.isLessThan(otherTwo), "2 は 2 より小さくない");
        assertTrue(two.isLessThan(three), "2 は 3 より小さい");
    }

    @Test
    @DisplayName("文字列表現")
    void string() {
        Days one = new Days(1);
        assertEquals("1日", one.toString());
    }
}