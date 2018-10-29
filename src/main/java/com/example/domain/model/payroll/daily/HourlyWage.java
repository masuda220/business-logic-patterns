package com.example.domain.model.payroll.daily;

import com.example.domain.type.RoundingType;
import com.example.domain.type.hour.HourAndMinute;
import com.example.domain.type.hour.Minute;
import com.example.domain.type.money.Amount;

public class HourlyWage {
    Amount amount;

    public HourlyWage(Amount amount) {
        this.amount = amount;
    }

    public Amount amountFor(HourAndMinute workTime, Minute minuteUnit, RoundingType roundingType) {
        Amount[] amountPerMinute = amount.divideAndRemainder(60);
        Minute workingMinute = new Minute(workTime.toMinute().value());
        Minute calculatingMinute = workingMinute.byUnit(minuteUnit, roundingType);
        return amountPerMinute[0].multiply(calculatingMinute.value());

    }

}
