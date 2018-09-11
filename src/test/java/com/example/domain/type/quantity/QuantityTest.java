package com.example.domain.type.quantity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.domain.type.quantity.unit.BoxUnit;
import com.example.domain.type.quantity.unit.PieceUnit;

class QuantityTest {

    @Test
    @DisplayName("文字列表現")
    void smoke() {
        Quantity one = new Quantity(1, new PieceUnit());
        assertEquals("1", one.toString());
    }

    @Test
    @DisplayName("ピース＋ピースの数量")
    void addPieceAndPiece() {
        Quantity one = new Quantity(1, new PieceUnit());
        Quantity other = new Quantity(2, new PieceUnit());

        Quantity expected = new Quantity(3, new PieceUnit());
        Quantity actual = one.add(other);

        assertEquals(expected.value, actual.value, "1ピース + 2ピース = 3ピース");
        assertEquals(expected.unit.getClass(), actual.unit.getClass(), "単位 = ピース");
    }

    @Test
    @DisplayName("ピース＋箱の数量")
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
    @DisplayName("箱＋ピースの数量")
    void addBoxAndPiece() {
        int piece = 20;
        Quantity one = new Quantity(3, new BoxUnit(piece));
        Quantity other = new Quantity(50, new PieceUnit());

        assertThrows(IllegalArgumentException.class, () -> one.add(other), "加算時のピース・箱変換 → 例外");
    }

    @Test
    @DisplayName("箱＋箱の数量")
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
    @DisplayName("異なる単位の箱の加算で例外")
    void addDifferentUnitBoxes() {
        int pieceOne = 20;
        Quantity one = new Quantity(1, new BoxUnit(pieceOne));
        int pieceOther = 24;
        Quantity other = new Quantity(3, new BoxUnit(pieceOther));

        assertThrows(IllegalArgumentException.class, () -> one.add(other));
    }

    @Test
    @DisplayName("ピースの加算オーバーフロー")
    void addPieceOverflow() {
        Quantity max = new Quantity(Integer.MAX_VALUE, new PieceUnit());
        Quantity other = new Quantity(1, new PieceUnit());

        assertThrows(ArithmeticException.class, () -> max.add(other));
    }

    @Test
    @DisplayName("箱の加算オーバーフロー")
    void addBoxOverflow() {
        int piece = 24;
        Quantity max = new Quantity(Integer.MAX_VALUE, new BoxUnit(piece));
        Quantity other = new Quantity(1, new BoxUnit(piece));

        assertThrows(ArithmeticException.class, () -> max.add(other));
    }

    @Test
    @DisplayName("ピース－ピースの数量")
    void subtractPieceFromPiece() {
        Quantity one = new Quantity(1, new PieceUnit());
        Quantity other = new Quantity(1, new PieceUnit());

        Quantity expected = new Quantity(0, new PieceUnit());
        Quantity actual = one.subtract(other);

        assertEquals(expected.value, actual.value, "1ピース - 1ピース = 0ピース");
        assertEquals(expected.unit.getClass(), actual.unit.getClass(), "単位 = ピース");
    }

    @Test
    @DisplayName("ピース－箱の数量")
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
    @DisplayName("減算時のピース・箱変換で例外")
    void subtractPieceFromBox() {
        int piece = 20;
        Quantity one = new Quantity(3, new BoxUnit(piece));
        Quantity other = new Quantity(22, new PieceUnit());

        assertThrows(IllegalArgumentException.class, () -> one.subtract(other));
    }

    @Test
    @DisplayName("箱－箱の数量")
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
    @DisplayName("異なる単位の箱の減算で例外")
    void subtractDifferentUnitBoxes() {
        int pieceOne = 20;
        Quantity one = new Quantity(1, new BoxUnit(pieceOne));
        int pieceOther = 24;
        Quantity other = new Quantity(3, new BoxUnit(pieceOther));

        assertThrows(IllegalArgumentException.class, () -> one.subtract(other));
    }

    @Test
    @DisplayName("ピースの減算オーバーフロー")
    void subtractPieceOverflow() {
        Quantity max = new Quantity(Integer.MIN_VALUE, new PieceUnit());
        Quantity other = new Quantity(1, new PieceUnit());

        assertThrows(ArithmeticException.class, () -> max.subtract(other));
    }

    @Test
    @DisplayName("箱の減算オーバーフロー")
    void subtractBoxOverflow() {
        int piece = 24;
        Quantity max = new Quantity(Integer.MIN_VALUE, new BoxUnit(piece));
        Quantity other = new Quantity(1, new BoxUnit(piece));

        assertThrows(ArithmeticException.class, () -> max.subtract(other));
    }

    @Test
    @DisplayName("数量の乗算")
    void multiply() {
        Quantity one = new Quantity(2, new PieceUnit());

        Quantity expected = new Quantity(6, new PieceUnit());
        Quantity actual = one.multiply(3);

        assertEquals(expected.value, actual.value, "2ピース * 3 = 6ピース");
        assertEquals(expected.unit.getClass(), actual.unit.getClass(), "単位 = ピース");

    }
}