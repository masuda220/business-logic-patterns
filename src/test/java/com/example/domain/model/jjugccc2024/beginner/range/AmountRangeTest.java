package com.example.domain.model.jjugccc2024.beginner.range;

import org.junit.jupiter.api.Test;

import static com.example.domain.model.jjugccc2024.beginner.range.Amount.一円;
import static org.junit.jupiter.api.Assertions.*;

class AmountRangeTest {

    private final Amount 下限 = Amount.of(2_000);
    private final Amount 上限 = Amount.of(5_000);
    private final AmountRange 金額範囲 = AmountRange.create(下限, 上限);

    @Test
    void が次の金額を含む() {
        Amount 金額 = Amount.of(3_500);
        assertTrue(金額範囲.が次の金額を含む(金額));
    }

    @Test
    void 下限() {
        assertTrue(金額範囲.が次の金額を含む(下限));
    }

    @Test
    void 下限より小さい() {
        assertFalse(金額範囲.が次の金額を含む(下限.引く(一円)));
    }

    @Test
    void 上限() {
        assertFalse(金額範囲.が次の金額を含む(上限));
    }

    @Test
    void 上限より小さい() {
        assertTrue(金額範囲.が次の金額を含む(上限.引く(一円)));
    }

}