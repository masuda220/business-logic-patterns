package com.example.domain.type.hour;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HourTimeTest {

    @Test()
    @DisplayName("コンストラクタテスト")
    void constructorText() {
        try {
            new HourTime("1", "23");    //時間が一桁の場合
            new HourTime("12", "3");    //分が一桁の場合
            new HourTime("12", "34");
        }
        catch(DateTimeParseException e) {
            fail();
        }
    }
}