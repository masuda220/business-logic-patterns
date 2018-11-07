package com.example.application.service.warikan;

import com.example.domain.model.warikan.Headcount;
import com.example.domain.model.warikan.Warikan;
import com.example.domain.type.money.Amount;
import com.example.domain.type.money.AmountUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarikanServiceTest {

    @Test
    @DisplayName("不足が発生する割り勘を行い不足が発生するテスト")
    void warikanWithShortage() {
        Amount totalAmount = new Amount(10090);
        Headcount headcount = new Headcount(10);
        Warikan actual = new WarikanService().warikanWithShortage(totalAmount, headcount, AmountUnit.ONE_HUNDRED);
        assertAll(
                "10,090円を10人で100円単位で不足のある割り勘",
                () -> assertTrue(actual.perPerson().isEqualTo(new Amount(1000)), "一人あたり1000円"),
                () -> assertTrue(actual.remainder().isEqualTo(new Amount(0)), "余り0円"),
                () -> assertTrue(actual.shortage().isEqualTo(new Amount(90)), "不足90円")
        );
    }

    @Test
    @DisplayName("余りが発生する割り勘を行い余りが発生するテスト")
    void warikanWithRemainder() {
        Amount totalAmount = new Amount(10090);
        Headcount headcount = new Headcount(10);
        Warikan actual = new WarikanService().warikanWithRemainder(totalAmount, headcount, AmountUnit.ONE_HUNDRED);
        assertAll(
                "10,090円を10人で100円単位で余りがある割り勘",
                () -> assertTrue(actual.perPerson().isEqualTo(new Amount(1100)), "一人あたり1100円"),
                () -> assertTrue(actual.remainder().isEqualTo(new Amount(910)), "余り910円"),
                () -> assertTrue(actual.shortage().isEqualTo(new Amount(0)), "不足0円")
        );
    }

    @Test
    @DisplayName("不足が発生する割り勘を行い余り不足のないテスト")
    void warikanNonShortage() {
        Amount totalAmount = new Amount(10000);
        Headcount headcount = new Headcount(5);
        Warikan actual = new WarikanService().warikanWithShortage(totalAmount, headcount, AmountUnit.ONE_THOUSAND);
        assertAll(
                "10,000円を5人で1,000円単位で割り勘",
                () -> assertTrue(actual.perPerson().isEqualTo(new Amount(2000)), "一人あたり2000円"),
                () -> assertTrue(actual.remainder().isEqualTo(new Amount(0)), "余り0円"),
                () -> assertTrue(actual.shortage().isEqualTo(new Amount(0)), "不足0円")
        );
    }

    @Test
    @DisplayName("余りが発生する割り勘を行い余り不足のないテスト")
    void warikanNonRemainder() {
        Amount totalAmount = new Amount(10000);
        Headcount headcount = new Headcount(5);
        Warikan actual = new WarikanService().warikanWithRemainder(totalAmount, headcount, AmountUnit.ONE_THOUSAND);
        assertAll(
                "10,000円を5人で1,000円単位で割り勘",
                () -> assertTrue(actual.perPerson().isEqualTo(new Amount(2000)), "一人あたり2000円"),
                () -> assertTrue(actual.remainder().isEqualTo(new Amount(0)), "余り0円"),
                () -> assertTrue(actual.shortage().isEqualTo(new Amount(0)), "不足0円")
        );
    }
}
