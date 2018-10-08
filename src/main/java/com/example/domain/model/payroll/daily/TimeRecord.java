package com.example.domain.model.payroll.daily;

import com.example.domain.type.hour.HourAndMinute;
import com.example.domain.type.hour.HourTime;
import com.example.domain.type.hour.HourTimeRange;
import com.example.domain.type.hour.Minute;

public class TimeRecord {
    HourTime start;
    HourTime end;
    Minute breaks;

    public TimeRecord(HourTime start, HourTime end, Minute breaks) {
        this.start = start;
        this.end = end;
        this.breaks = breaks;
    }

    HourAndMinute workTime() {
        HourAndMinute hourAndMinute = new HourTimeRange(start, end).between();
        Minute workingMinute = hourAndMinute.toMinute().subtract(breaks);
        return HourAndMinute.from(workingMinute);
    }

}
