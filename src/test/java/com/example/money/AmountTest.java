package com.example.money;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmountTest {

    @Test()
    void addOverflow() {
        Amount max = new Amount(Long.MAX_VALUE);
        Amount one = new Amount(1);
        assertThrows(ArithmeticException.class, () -> max.add(one),"加算オーバーフロー");
    }
}