package com.example.domain.type.hour;

import java.time.DateTimeException;

/**
 * 分(数)
 */
public class Minute {
    int value;

    public Minute (int time) {
        value = time;
    }

    Minute add(Minute minute) {
        return new Minute(value + minute.value);
    }

    public Minute subtract(Minute minute) {
        if (value < minute.value) {
            // FIXME エラーメッセージをわかりやすく
            throw new DateTimeException("Error of minus time.");
        }
        return new Minute(value - minute.value);
    }

    @Override
    public String toString() {
        return String.format("%d", value);
    }

    public int value() {
        return value;
    }
}
