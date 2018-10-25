package com.example.application.service.payroll;

import com.example.domain.model.payroll.daily.DailyPay;
import com.example.domain.model.payroll.daily.HourlyWage;
import com.example.domain.model.payroll.daily.MinuteUnit;
import com.example.domain.model.payroll.daily.TimeRecord;
import com.example.domain.type.money.Amount;

public class DailyPayroll {

    public Amount amountFor(TimeRecord timeRecord, MinuteUnit minuteUnit, HourlyWage hourlyWage) {
        DailyPay dailyPay = new DailyPay(timeRecord, minuteUnit, hourlyWage);
        return dailyPay.amount();
    }

}
