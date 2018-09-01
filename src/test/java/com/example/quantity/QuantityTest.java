package com.example.quantity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {

    @Test
    void smoke() {
        Quantity one = new Quantity(1);
        assertEquals("1", one.toString(),"文字列表現");
    }
}