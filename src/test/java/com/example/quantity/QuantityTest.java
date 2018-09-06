package com.example.quantity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.quantity.unit.BoxUnit;
import com.example.quantity.unit.PieceUnit;

class QuantityTest {

    @Test
    void smoke() {
        Quantity one = new Quantity(1, new PieceUnit());
        assertEquals("1", one.toString(), "文字列表現");
    }

    @Test
    void addPieceAndPiece() {
        Quantity one = new Quantity(1, new PieceUnit());
        Quantity other = new Quantity(2, new PieceUnit());

        Quantity expected = new Quantity(3, new PieceUnit());
        Quantity actual = one.add(other);

        assertEquals(expected.value, actual.value, "1ピース + 2ピース = 3ピース");
        assertEquals(expected.unit.getClass(), actual.unit.getClass(), "単位 = ピース");
    }

    @Test
    void addPieceAndBox() {
        int piece = 12;
        Quantity one = new Quantity(1, new PieceUnit());
        Quantity other = new Quantity(2, new BoxUnit(piece));

        Quantity expected = new Quantity(25, new PieceUnit());
        Quantity actual = one.add(other);

        assertEquals(expected.value, actual.value, "1ピース + 2箱(*12ピース) = 25ピース");
        assertEquals(expected.unit.getClass(), actual.unit.getClass(), "単位 = ピース");
    }

    @Test
    void addBoxAndPiece() {
        int piece = 20;
        Quantity one = new Quantity(3, new BoxUnit(piece));
        Quantity other = new Quantity(50, new PieceUnit());

        Quantity expected = new Quantity(5, new BoxUnit(piece));
        Quantity actual = one.add(other);

        assertEquals(expected.value, actual.value, "3箱(*20ピース) + 50ピース = 5箱");
        assertEquals(expected.unit.getClass(), actual.unit.getClass(), "単位 = 箱");
    }

    @Test
    void addBoxAndBox() {
        int piece = 24;
        Quantity one = new Quantity(1, new BoxUnit(piece));
        Quantity other = new Quantity(3, new BoxUnit(piece));

        Quantity expected = new Quantity(4, new BoxUnit(piece));
        Quantity actual = one.add(other);

        assertEquals(expected.value, actual.value, "1箱 + 3箱 = 4箱");
        assertEquals(expected.unit.getClass(), actual.unit.getClass(), "単位 = 箱");
    }

    @Test
    void addOverflow() {
        Quantity max = new Quantity(Integer.MAX_VALUE, new PieceUnit());
        Quantity other = new Quantity(1, new PieceUnit());

        assertThrows(ArithmeticException.class, () -> max.add(other), "加算オーバーフロー");
    }

    @Test
    void subtractPieceFromPiece() {
        Quantity one = new Quantity(1, new PieceUnit());
        Quantity other = new Quantity(1, new PieceUnit());

        Quantity expected = new Quantity(0, new PieceUnit());
        Quantity actual = one.subtract(other);

        assertEquals(expected.value, actual.value, "1ピース - 1ピース = 0ピース");
        assertEquals(expected.unit.getClass(), actual.unit.getClass(), "単位 = ピース");
    }

    @Test
    void subtractBoxFromPiece() {
        int piece = 12;
        Quantity one = new Quantity(26, new PieceUnit());
        Quantity other = new Quantity(2, new BoxUnit(piece));

        Quantity expected = new Quantity(2, new PieceUnit());
        Quantity actual = one.subtract(other);

        assertEquals(expected.value, actual.value, "26ピース - 2箱(*12ピース) = 2ピース");
        assertEquals(expected.unit.getClass(), actual.unit.getClass(), "単位 = ピース");
    }

    @Test
    void subtractPieceFromBox() {
        int piece = 20;
        Quantity one = new Quantity(3, new BoxUnit(piece));
        Quantity other = new Quantity(22, new PieceUnit());

        Quantity expected = new Quantity(2, new BoxUnit(piece));
        Quantity actual = one.subtract(other);

        assertEquals(expected.value, actual.value, "3箱(*20ピース) - 22ピース = 2箱");
        assertEquals(expected.unit.getClass(), actual.unit.getClass(), "単位 = 箱");
    }

    @Test
    void subtractBoxFromBox() {
        int piece = 24;
        Quantity one = new Quantity(5, new BoxUnit(piece));
        Quantity other = new Quantity(3, new BoxUnit(piece));

        Quantity expected = new Quantity(2, new BoxUnit(piece));
        Quantity actual = one.subtract(other);

        assertEquals(expected.value, actual.value, "5箱 - 3箱 = 2箱");
        assertEquals(expected.unit.getClass(), actual.unit.getClass(), "単位 = 箱");
    }

    @Test
    void subtractOverflow() {
        Quantity max = new Quantity(Integer.MIN_VALUE, new PieceUnit());
        Quantity other = new Quantity(1, new PieceUnit());

        assertThrows(ArithmeticException.class, () -> max.subtract(other), "減算オーバーフロー");
    }

    @Test
    void multiply() {
        Quantity one = new Quantity(2, new PieceUnit());

        Quantity expected = new Quantity(6, new PieceUnit());
        Quantity actual = one.multiply(3);

        assertEquals(expected.value, actual.value, "2ピース * 3 = 6ピース");
        assertEquals(expected.unit.getClass(), actual.unit.getClass(), "単位 = ピース");

    }
}