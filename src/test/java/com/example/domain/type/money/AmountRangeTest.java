package com.example.domain.type.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmountRangeTest {

    @Test()
    @DisplayName("境界値テスト")
    void contains() {
        Amount min = new Amount(1);
        Amount max = new Amount(100);
        AmountRange ar = new AmountRange(min, max);

        Amount targetA1 = new Amount(1);
        assertTrue(ar.contains(targetA1), "1 は 最小値(1) 以上 最大値(100) 未満");

        Amount targetA2 = new Amount(99);
        assertTrue(ar.contains(targetA2), "1 は 最小値(1) 以上 最大値(100) 未満");

        Amount targetB = new Amount(0);
        assertFalse(ar.contains(targetB), "0 は 最小値(1)未満");

        Amount targetC = new Amount(100);
        assertFalse(ar.contains(targetC), "100 は 最大値(100)以上");
    }
}