package com.example.application.service.warikan;

import com.example.domain.model.warikan.Headcount;
import com.example.domain.type.money.Amount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WarikanTest {

    // TODO テストケース見直し、追加

    @Test
    @DisplayName("支払総額に対して、多めに割り勘するテスト")
    void testRoundUp() {
        Amount totalAmount = new Amount(10090);
        Headcount headcount = new Headcount(10);
        Amount[] actual = new Warikan().warikan(totalAmount, headcount);
        assertAll(
                () -> assertEquals(2, actual.length),
                () -> assertTrue(actual[0].isEqualTo(new Amount(1100)), "一人あたり1100円"),
                () -> assertTrue(actual[1].isEqualTo(new Amount(910)), "超過910円")
        );
    }

    @Test
    @DisplayName("支払総額に対して、少なめに割り勘するテスト")
    void testRoundDown() {
        Amount totalAmount = new Amount(10090);
        Headcount headcount = new Headcount(10);
        Amount[] actual = new Warikan().warikanRoundDown(totalAmount, headcount);
        assertAll(
                () -> assertEquals(2, actual.length),
                () -> assertTrue(actual[0].isEqualTo(new Amount(1000)), "一人あたり1000円"),
                () -> assertTrue(actual[1].isEqualTo(new Amount(90)), "端数90円")
        );
    }
}
