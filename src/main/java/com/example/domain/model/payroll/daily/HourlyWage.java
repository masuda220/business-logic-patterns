package com.example.domain.model.payroll.daily;

import com.example.domain.type.hour.HourAndMinute;
import com.example.domain.type.money.Amount;

public class HourlyWage {
    Amount amount;

    public HourlyWage(Amount amount) {
        this.amount = amount;
    }

    Amount amountFor(HourAndMinute workTime) {
        Amount[] amountPerMinute = amount.divideAndRemainder(60);
        int minute = workTime.toMinute().value();
        return amountPerMinute[0].multiply(minute);

    }

}
