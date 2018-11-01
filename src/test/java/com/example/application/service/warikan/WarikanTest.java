package com.example.application.service.warikan;

import com.example.domain.model.warikan.Bill;
import com.example.domain.model.warikan.Headcount;
import com.example.domain.type.money.Amount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WarikanTest {

    // TODO テストケース見直し、追加

    @Test
    @DisplayName("テスト")
    void test() {
        Bill bill = Bill.of(new Amount(10090));
        Headcount headcount = new Headcount(10);
        Amount[] actual = new Warikan().warikan(bill, headcount);
        assertAll(
                () -> assertEquals(2, actual.length),
                () -> assertTrue(actual[0].isEqualTo(new Amount(1000)), "一人あたり1000円"),
                () -> assertTrue(actual[1].isEqualTo(new Amount(90)), "端数90円")
        );
    }
}
