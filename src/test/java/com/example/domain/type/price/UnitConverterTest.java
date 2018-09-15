package com.example.domain.type.price;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.domain.type.money.Amount;
import com.example.domain.type.quantity.unit.BoxUnit;
import com.example.domain.type.quantity.unit.PieceUnit;
import com.example.domain.type.quantity.unit.Unit;

class UnitConverterTest {

    @Test
    @DisplayName("ピースからピースへの変換")
    void pieceToPiece() {
        UnitPrice unitPrice = new UnitPrice(new Amount(95), new PieceUnit());
        UnitConverter unitConverter = new UnitConverter(new PieceUnit());

        UnitPrice actual = unitConverter.toPiece(unitPrice);

        assertTrue(actual.amount.isEqualTo(new Amount(95)));
        assertTrue(actual.unit.isEqualTo(new PieceUnit()));
    }

    @Test
    @DisplayName("ピースから箱への変換")
    void pieceToBox() {
        UnitPrice unitPrice = new UnitPrice(new Amount(95), new PieceUnit());
        Unit boxUnit = new BoxUnit(24);
        UnitConverter unitConverter = new UnitConverter(boxUnit);

        UnitPrice actual = unitConverter.toBox(unitPrice);

        assertTrue(actual.amount.isEqualTo(new Amount(95 * 24)));
        assertTrue(actual.unit.isEqualTo(boxUnit));
    }

    @Test
    @DisplayName("箱からピースへの変換")
    void boxToPiece() {
        Unit boxUnit = new BoxUnit(24);
        UnitPrice unitPrice = new UnitPrice(new Amount(95 * 24), boxUnit);
        Unit pieceUnit = new PieceUnit();
        UnitConverter unitConverter = new UnitConverter(boxUnit);

        UnitPrice actual = unitConverter.toPiece(unitPrice);

        assertTrue(actual.amount.isEqualTo(new Amount(95)));
        assertTrue(actual.unit.isEqualTo(pieceUnit));
    }

    @Test
    @DisplayName("箱から箱への変換")
    void boxToBox() {
        Unit boxUnit = new BoxUnit(24);
        UnitPrice unitPrice = new UnitPrice(new Amount(95 * 24), boxUnit);
        UnitConverter unitConverter = new UnitConverter(boxUnit);

        UnitPrice actual = unitConverter.toBox(unitPrice);

        assertTrue(actual.amount.isEqualTo(new Amount(95 * 24)));
        assertTrue(actual.unit.isEqualTo(boxUnit));
    }

    @Test
    @DisplayName("箱からピースへの変換で割り切れない場合は例外")
    void boxToPieceException() {
        Unit boxUnit = new BoxUnit(24);
        UnitPrice unitPrice = new UnitPrice(new Amount(95 * 20), boxUnit);
        UnitConverter unitConverter = new UnitConverter(boxUnit);

        assertThrows(IllegalArgumentException.class, () -> unitConverter.toPiece(unitPrice));
    }

    @Test
    @DisplayName("ピース数の異なる箱同士の変換は例外")
    void differentBoxToBoxException() {
        Unit box20 = new BoxUnit(20);
        UnitPrice unitPrice = new UnitPrice(new Amount(95 * 20), box20);
        Unit box24 = new BoxUnit(24);
        UnitConverter unitConverter = new UnitConverter(box24);
        assertThrows(IllegalArgumentException.class, () -> unitConverter.toBox(unitPrice));
    }

}
