package com.example.application.service.payroll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.domain.model.payroll.daily.HourlyWage;
import com.example.domain.model.payroll.daily.TimeRecord;
import com.example.domain.type.hour.HourTime;
import com.example.domain.type.hour.Minute;
import com.example.domain.type.money.Amount;

class DailyPayrollTest extends DailyPayroll {

    @Test
    @DisplayName("時間単位日給算出")
    void exactlyHours() {
        HourTime start = new HourTime(9, 0);
        HourTime end = new HourTime(18, 0);
        Minute breaks = new Minute(60);
        TimeRecord timeRecord = new TimeRecord(start, end, breaks);
        HourlyWage hourlyWage = new HourlyWage(new Amount(820));

        Amount actual = new DailyPayroll().amountFor(timeRecord, hourlyWage);

        assertTrue(new Amount((int) ((820 / 60) * 60 * 8)).isEqualTo(actual));

    }

    @Test
    @DisplayName("時間・分単位日給算出")
    void hourAndMinute() {
        HourTime start = new HourTime(10, 0);
        HourTime end = new HourTime(17, 30);
        Minute breaks = new Minute(60);
        TimeRecord timeRecord = new TimeRecord(start, end, breaks);
        HourlyWage hourlyWage = new HourlyWage(new Amount(820));

        Amount actual = new DailyPayroll().amountFor(timeRecord, hourlyWage);

        assertTrue(new Amount((int) (820 / 60.0d) * (60 * 6 + 30)).isEqualTo(actual));

    }

}
