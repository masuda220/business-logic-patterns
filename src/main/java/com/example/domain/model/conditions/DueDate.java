package com.example.domain.model.conditions;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * 貸出期限
 */
class DueDate {
    LocalDate value;
    static final int 最大貸出日数 = 14;

    DueDate(LocalDate value) {
        this.value = value;
    }

    DaysOfDelay daysOfDelay(LocalDate 判定日) {
        Long 日数 = value.until(判定日, ChronoUnit.DAYS);
        return new DaysOfDelay(日数.intValue());
    }

    static DueDate from(LocalDate loanDate) {
        LocalDate 期限日 = loanDate.plusDays(最大貸出日数);
        return new DueDate(期限日);
    }
}
