package com.example.domain.model.conditions;

/**
 * 日数
 */
class DaysOfDelay {
    int value;

    DaysOfDelay(int value) {
        this.value = value;
    }

    public boolean lessThan(int other) {
        return value < other;
    }
}
