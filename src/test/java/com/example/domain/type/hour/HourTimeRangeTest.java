package com.example.domain.type.hour;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HourTimeRangeTest {

    @Test()
    @DisplayName("時間間隔取得テスト")
    void differenceTime() {
        HourTime from = new HourTime("12", "31");
        HourTime to = new HourTime("23", "10");

        HourTimeRange hourTimeGange = new HourTimeRange(from, to);
        HourAndMinute result = hourTimeGange.differenceTime();
        
        assertEquals(result.toString(), "10:39"); result.toString();
    }
}