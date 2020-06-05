package com.example.domain.model.conditions;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestrictionTest {

    @ParameterizedTest
    @CsvSource({
            // 会員種別、貸出日、期待値
            "大人, 2020-01-04, 貸出５冊まで",
            "大人, 2020-01-03, 貸出不可",
            "大人, 2019-12-31, 貸出不可",
            "大人, 2019-12-30, 貸出不可",
            "子供, 2020-01-04,貸出７冊まで",
            "子供, 2020-01-03, 貸出４冊まで",
            "子供, 2019-12-31, 貸出４冊まで",
            "子供, 2019-12-30, 貸出不可"
    })
    void 貸出制限の判定ができる(MemberType memberType, String loanDate, RestrictionOfQuantity expected) {
        LocalDate loaned = LocalDate.parse(loanDate);
        DueDate dueDate = DueDate.from(loaned);

        LocalDate currentDate = LocalDate.parse("2020-01-20");
        DaysOfDelay daysOfDelay = dueDate.daysOfDelay(currentDate);
        DelayStatus delayStatus = DelayStatus.level(daysOfDelay);
        RestrictionTable table = new RestrictionTable();

        assertEquals(expected, table.lookup(delayStatus, memberType));
    }
}