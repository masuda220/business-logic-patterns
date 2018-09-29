package com.example.domain.model.price;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.domain.type.money.Amount;
import com.example.domain.type.quantity.Quantity;
import com.example.domain.type.quantity.unit.BoxUnit;
import com.example.domain.type.quantity.unit.PieceUnit;

class UnitPriceTest {

    @Test
    @DisplayName("ピース×ピースの合計金額")
    void multiplyPiece() {
        UnitPrice unitPrice = new UnitPrice(new Amount(95), new PieceUnit());
        Quantity quantity = new Quantity(24, new PieceUnit());

        Amount actual = unitPrice.multiply(quantity);

        assertTrue(new Amount(2280).isEqualTo(actual), "1ピース95円 * 24ピース = 2280円");
    }

    @Test
    @DisplayName("箱×箱の合計金額")
    void multiplyBox() {
        int piece = 24;
        UnitPrice unitPrice = new UnitPrice(new Amount(2280), new BoxUnit(piece));
        Quantity quantity = new Quantity(5, new BoxUnit(piece));

        Amount actual = unitPrice.multiply(quantity);

        assertTrue(new Amount(11400).isEqualTo(actual), "1箱2280円 * 5箱 = 11400円");
    }

    @Test
    @DisplayName("ピース×箱の合計金額")
    void multiplyPieceAndBox() {
        int piece = 24;
        UnitPrice unitPrice = new UnitPrice(new Amount(95), new PieceUnit());
        Quantity quantity = new Quantity(6, new BoxUnit(piece));

        Amount actual = unitPrice.multiply(quantity);

        assertTrue(new Amount(13680).isEqualTo(actual), "1ピース95円 * 24ピース6箱 = 13680円");
    }

    @Test
    @DisplayName("箱×ピースで例外")
    void multiplyBoxAndPiece() {
        int piece = 24;
        UnitPrice unitPrice = new UnitPrice(new Amount(2280), new BoxUnit(piece));
        Quantity quantity = new Quantity(5, new PieceUnit());

        assertThrows(IllegalArgumentException.class, () -> unitPrice.multiply(quantity));
    }

    @Test
    @DisplayName("ピースからピースへの変換")
    void pieceToPiece() {
        UnitPrice unitPrice = new UnitPrice(new Amount(95), new PieceUnit());
        UnitPrice expected = new UnitPrice(new Amount(95), new PieceUnit());

        UnitPrice actual = unitPrice.convertTo(new PieceUnit());

        assertTrue(expected.amount.isEqualTo(actual.amount));
        assertTrue(expected.unit.isEqualTo(actual.unit));
    }

    @Test
    @DisplayName("ピースから箱への変換")
    void pieceToBox() {
        UnitPrice unitPrice = new UnitPrice(new Amount(95), new PieceUnit());
        UnitPrice expected = new UnitPrice(new Amount(95 * 24), new BoxUnit(24));

        UnitPrice actual = unitPrice.convertTo(new BoxUnit(24));

        assertTrue(expected.amount.isEqualTo(actual.amount));
        assertTrue(expected.unit.isEqualTo(actual.unit));
    }

    @Test
    @DisplayName("箱からピースへの変換")
    void boxToPiece() {
        UnitPrice unitPrice = new UnitPrice(new Amount(95 * 24), new BoxUnit(24));
        UnitPrice expected = new UnitPrice(new Amount(95), new PieceUnit());

        UnitPrice actual = unitPrice.convertTo(new PieceUnit());

        assertTrue(expected.amount.isEqualTo(actual.amount));
        assertTrue(expected.unit.isEqualTo(actual.unit));
    }

    @Test
    @DisplayName("箱から箱への変換")
    void boxToBox() {
        UnitPrice unitPrice = new UnitPrice(new Amount(95 * 24), new BoxUnit(24));
        UnitPrice expected = new UnitPrice(new Amount(95 * 24), new BoxUnit(24));

        UnitPrice actual = unitPrice.convertTo(new BoxUnit(24));

        assertTrue(expected.amount.isEqualTo(actual.amount));
        assertTrue(expected.unit.isEqualTo(actual.unit));
    }

    @Test
    @DisplayName("異なるピース数の箱への変換で例外")
    void differentPieceBox() {
        UnitPrice unitPrice = new UnitPrice(new Amount(95 * 24), new BoxUnit(24));
        assertThrows(IllegalArgumentException.class, () -> unitPrice.convertTo(new BoxUnit(50)));
    }

    @Test
    @DisplayName("箱からピースへの変換で端数が出る場合は例外")
    void boxToPieceException() {
        UnitPrice unitPrice = new UnitPrice(new Amount(95 * 25), new BoxUnit(24));
        assertThrows(ArithmeticException.class, () -> unitPrice.convertTo(new PieceUnit()));
    }


}
