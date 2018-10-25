package com.example.domain.model.payroll.daily;

import com.example.domain.type.money.Amount;

public class DailyPay {
    public DailyPay(TimeRecord timeRecord, MinuteUnit minuteUnit, HourlyWage hourlyWage) {
        this.timeRecord = timeRecord;
        this.minuteUnit = minuteUnit;
        this.hourlyWage = hourlyWage;
    }

    TimeRecord timeRecord;
    MinuteUnit minuteUnit;
    HourlyWage hourlyWage;


    public Amount amount() {
        return hourlyWage.amountFor(timeRecord.workTime(), minuteUnit);
    }
}
