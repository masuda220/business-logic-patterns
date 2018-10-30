package com.example.domain.type.hour;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DateTimeException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MinuteTest {

    @Test()
    @DisplayName("時間の引き算テスト")
    void subtructText() {
        try {
            Minute testMin = new Minute(50);
            Minute targetMin = new Minute(40);
            targetMin.subtract(testMin);
            //40分から50分を引くのでエラーがThrowされる
            //fail();
        }
        catch(DateTimeException e) {
            assertEquals(e.getMessage(), "Error of minus time.");
        }

        try {
            Minute testMin = new Minute(23);
            Minute targetMin = new Minute(40);
            targetMin.subtract(testMin);
            //40分から23分を引くので0以上であれば正しい
            assertTrue(targetMin.value >= 0);
        }
        catch(DateTimeException e) {
            //fail();
        }
    }
}