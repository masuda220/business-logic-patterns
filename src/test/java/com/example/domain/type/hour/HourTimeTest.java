package com.example.domain.type.hour;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HourTimeTest {

    @Test()
    @DisplayName("コンストラクタテスト")
    void constructorText() {
        try {
            HourTime ht = new HourTime(12, 34);

            assertEquals(ht.value.getHour(), 12);
            assertEquals(ht.value.getMinute(), 34);
        
        }
        catch(DateTimeParseException e) {
            fail();
        }
    }
}