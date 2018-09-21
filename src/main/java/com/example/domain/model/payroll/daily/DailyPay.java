package com.example.domain.model.payroll.daily;

import com.example.domain.type.money.Amount;

public class DailyPay {
    public DailyPay(TimeRecord timeRecord, HourlyWage hourlyWage) {
        this.timeRecord = timeRecord;
        this.hourlyWage = hourlyWage;
    }

    TimeRecord timeRecord;
    HourlyWage hourlyWage;


    public Amount amount() {
        return null; // TODO 実装
    }
}
