package com.example.domain.type.hour;

import java.math.BigDecimal;
import java.time.DateTimeException;

import com.example.domain.type.RoundingType;
import com.example.domain.type.hour.unit.MinuteUnit;

/**
 * 分(数)
 */
public class Minute {
    int value;

    public Minute(int time) {
        value = time;
    }

    Minute add(Minute minute) {
        value += minute.value;
        return new Minute(value);
    }

    public Minute subtract(Minute minute) {
        if (value - minute.value < 0) {
            // FIXME エラーメッセージをわかりやすく
            throw new DateTimeException("Error of minus time.");
        }
        value -= minute.value;
        return new Minute(value);
    }

    public Minute multiply(int multiplier) {
        return new Minute(Math.multiplyExact(value, multiplier));
    }

    public Minute divideExact(int divisor) {
        if (value % divisor == 0)
            return new Minute(value / divisor);

        throw new ArithmeticException();
    }

    public Minute divide(int divisor, RoundingType roundingType) {
        BigDecimal quotient = new BigDecimal(value).divide(new BigDecimal(divisor), roundingType.mode());
        return new Minute(quotient.intValue());
    }

    public Minute byUnit(MinuteUnit minuteUnit, RoundingType roundingType) {
        Minute units = this.divide(minuteUnit.minute().value, roundingType);
        return minuteUnit.minute().multiply(units.value);
    }

    @Override
    public String toString() {
        return String.format("%d", value);
    }

    public int value() {
        return value;
    }

}
