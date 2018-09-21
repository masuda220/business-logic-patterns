package com.example.domain.model.payroll.daily;

import com.example.domain.type.hour.HourAndMinute;
import com.example.domain.type.hour.HourTime;
import com.example.domain.type.hour.Minute;

public class TimeRecord {
    HourTime start;
    HourTime end;
    Minute breaks;

    // TODO コンストラクタ

    HourAndMinute workTime() {
        return null; // TODO 実装
    }
}
