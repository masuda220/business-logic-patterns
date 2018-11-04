package com.example.application.service.warikan;

import com.example.domain.model.warikan.Headcount;
import com.example.domain.model.warikan.Warikan;
import com.example.domain.type.money.Amount;
import com.example.domain.type.money.Amount.AmountUnit;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WarikanServiceTest {

    // TODO テストケース見直し、追加（余り不足がともに0, 1000円単位で丸めなど）

    @Test
    @DisplayName("支払総額に対して、多めに割り勘するテスト")
    void testRoundUp() {
        Amount totalAmount = new Amount(10090);
        Headcount headcount = new Headcount(10);
        Warikan actual = new WarikanService().warikanWithRemainder(totalAmount, headcount, AmountUnit.ONE_HUNDRED);
        assertAll(
                () -> assertTrue(actual.perPerson().isEqualTo(new Amount(1100)), "一人あたり1100円"),
                () -> assertTrue(actual.remainder().isEqualTo(new Amount(910)), "余り910円"),
                () -> assertTrue(actual.shortage().isEqualTo(new Amount(0)), "不足0円")
        );
    }

    @Test
    @DisplayName("支払総額に対して、少なめに割り勘するテスト")
    void testRoundDown() {
        Amount totalAmount = new Amount(10090);
        Headcount headcount = new Headcount(10);
        Warikan actual = new WarikanService().warikanWithShortage(totalAmount, headcount, AmountUnit.ONE_HUNDRED);
        assertAll(
                () -> assertTrue(actual.perPerson().isEqualTo(new Amount(1000)), "一人あたり1000円"),
                () -> assertTrue(actual.remainder().isEqualTo(new Amount(0)), "余り0円"),
                () -> assertTrue(actual.shortage().isEqualTo(new Amount(90)), "不足90円")
        );
    }
}
