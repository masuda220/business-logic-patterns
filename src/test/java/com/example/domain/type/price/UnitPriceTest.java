package com.example.domain.type.price;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.domain.type.money.Amount;
import com.example.domain.type.quantity.Quantity;
import com.example.domain.type.quantity.unit.BoxUnit;
import com.example.domain.type.quantity.unit.PieceUnit;

class UnitPriceTest {

    @Test
    void multiplyPiece() {
        UnitPrice unitPrice = new UnitPrice(new Amount(95), new PieceUnit());
        Quantity quantity = new Quantity(24, new PieceUnit());

        Amount actual = unitPrice.multiply(quantity);

        assertTrue(new Amount(2280).isEqualTo(actual), "1ピース95円 * 24ピース = 2280円");
    }

    @Test
    void multiplyBox() {
        int piece = 24;
        UnitPrice unitPrice = new UnitPrice(new Amount(2280), new BoxUnit(piece));
        Quantity quantity = new Quantity(5, new BoxUnit(piece));

        Amount actual = unitPrice.multiply(quantity);

        assertTrue(new Amount(11400).isEqualTo(actual), "1箱2280円 * 5箱 = 11400円");
    }

    @Test
    void multiplyPieceAndBox() {
        int piece = 24;
        UnitPrice unitPrice = new UnitPrice(new Amount(95), new PieceUnit());
        Quantity quantity = new Quantity(6, new BoxUnit(piece));

        Amount actual = unitPrice.multiply(quantity);

        assertTrue(new Amount(13680).isEqualTo(actual), "1ピース95円 * 24ピース6箱 = 13680円");
    }

    @Test
    void multiplyBoxAndPiece() {
        int piece = 24;
        UnitPrice unitPrice = new UnitPrice(new Amount(2280), new BoxUnit(piece));
        Quantity quantity = new Quantity(5, new PieceUnit());

        assertThrows(IllegalArgumentException.class, () -> unitPrice.multiply(quantity), "ピース・箱の乗算で例外");
    }

}
