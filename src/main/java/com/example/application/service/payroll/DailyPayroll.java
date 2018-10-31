package com.example.application.service.payroll;

import com.example.domain.model.payroll.daily.DailyPay;
import com.example.domain.model.payroll.daily.HourlyWage;
import com.example.domain.model.payroll.daily.TimeRecord;
import com.example.domain.type.RoundingType;
import com.example.domain.type.hour.unit.MinuteUnit;
import com.example.domain.type.money.Amount;

public class DailyPayroll {

    public Amount amountFor(TimeRecord timeRecord, MinuteUnit minuteUnit, RoundingType roundingType,
            HourlyWage hourlyWage) {
        DailyPay dailyPay = new DailyPay(timeRecord, minuteUnit, roundingType, hourlyWage);
        return dailyPay.amount();
    }

}
