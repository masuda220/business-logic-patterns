package com.example.domain.type.hour;

import java.time.DateTimeException;

/**
 * 分(数)
 */
public class Minute {
    int value;

    Minute (int time) {
        value = time;
    }

    Minute add(Minute minute) {
        value += minute.value;
        return new Minute(value);
    }

    Minute subtract(Minute minute) {
        if (value - minute.value < 0) {
            // FIXME エラーメッセージをわかりやすく
            throw new DateTimeException("Error of minus time.");
        }
        value -= minute.value;
        return new Minute(value);
    }

    @Override
    public String toString() {
        return String.format("%d", value);
    } 
}
