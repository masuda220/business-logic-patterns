package com.example.domain.model.conditions;

/**
 * 遅延状態
 */
enum DelayStatus {
    遅延日数３日未満,
    遅延日数７日未満,
    それ以外;

    static DelayStatus level(DaysOfDelay daysOfDelay) {
        if (daysOfDelay.lessThan(3)) return 遅延日数３日未満;
        if (daysOfDelay.lessThan(7)) return 遅延日数７日未満;
        return それ以外;
    }
}
