package com.example.domain.model.conditions;

import com.example.domain.model.conditions.map.RestrictionMap;
import com.example.domain.model.conditions.table.RestrictionTable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestrictionTest {

    static RestrictionTable table = new RestrictionTable();
    static RestrictionMap map = new RestrictionMap();

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

        assertEquals(expected, table.lookup(delayStatus, memberType));
        assertEquals(expected, map.of(new DelayOfMember(delayStatus, memberType)));
    }
}