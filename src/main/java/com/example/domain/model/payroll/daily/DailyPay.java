package com.example.domain.model.payroll.daily;

import com.example.domain.type.RoundingType;
import com.example.domain.type.hour.unit.MinuteUnit;
import com.example.domain.type.money.Amount;

public class DailyPay {
    public DailyPay(TimeRecord timeRecord, MinuteUnit minuteUnit, RoundingType roundingType, HourlyWage hourlyWage) {
        this.timeRecord = timeRecord;
        this.minuteUnit = minuteUnit;
        this.roundingType = roundingType;
        this.hourlyWage = hourlyWage;
    }

    TimeRecord timeRecord;
    MinuteUnit minuteUnit;
    RoundingType roundingType;
    HourlyWage hourlyWage;

    public Amount amount() {
        return hourlyWage.amountFor(timeRecord.workTime(), minuteUnit, roundingType);
    }
}
