package com.example.domain.model.payroll.daily;

import com.example.domain.type.hour.Minute;

public class MinuteUnit {

    Minute minute;

    boolean isRoundOff;

    public MinuteUnit(Minute minute, boolean isRoundOff) {
        this.minute = minute;
        this.isRoundOff = isRoundOff;
    }

    public int toWageMinute(int target) {
        if (isRoundOff) {
            double val = (double) target / minute.value();
            return minute.value() * (int) Math.round(val);
        }

        return minute.value() * Math.floorDiv(target, minute.value());

    }

}
