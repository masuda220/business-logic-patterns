package com.example.domain.model.jjugccc2024.beginner.range;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateRangeTest {

    private final LocalDate 開始日 = LocalDate.parse("2020-02-05");
    private final LocalDate 終了日 = LocalDate.parse("2020-03-20");
    private final DateRange 期間 = DateRange.create(開始日, 終了日);

    @Test
    void 期間内() {
        LocalDate 日付 = LocalDate.parse("2020-02-22");
        assertTrue(期間.期間内(日付));
    }

    @Test
    void 初日() {
        assertTrue(期間.期間内(開始日));
    }

    @Test
    void 最終日() {
        assertTrue(期間.期間内(終了日));
    }

    @Test
    void 開始日前() {
        LocalDate 日付 = 開始日.minusDays(1);
        assertFalse(期間.期間内(日付));
    }

    @Test
    void 終了日後() {
        LocalDate 日付 = 終了日.plusDays(1);
        assertFalse(期間.期間内(日付));
    }

}