package com.example.domain.model.jjugccc2024.beginner.range;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * 日付範囲
 */
class DateRange {
    LocalDate 開始日; //含む
    LocalDate 終了日; //含む

    private DateRange(LocalDate 開始日, LocalDate 終了日) {
        this.開始日 = 開始日;
        this.終了日 = 終了日;
    }

    boolean 期間内(LocalDate 日付) {
        if (日付.isBefore(開始日)) return false;
        if (日付.isAfter(終了日)) return false;
        return true;
    }

    static DateRange create(LocalDate 開始日, LocalDate 終了日) {
        if (終了日.isBefore(開始日)) throw new DateTimeException("終了日が開始日より前");
        return new DateRange(開始日, 終了日);
    }
}
