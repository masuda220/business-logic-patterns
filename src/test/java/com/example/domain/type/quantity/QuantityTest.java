package com.example.domain.type.quantity;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.example.domain.type.quantity.unit.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

class QuantityTest {

    @Test
    @DisplayName("文字列表現")
    void smoke() {
        Quantity one = new Quantity(1, Unit.PIECE);
        assertEquals("1個", one.toString());
    }

    @DisplayName("足し算")
    @ParameterizedTest
    @MethodSource
    void add(String message, int oneValue, int anotherValue, int expectedValue, Unit unit) {
        Quantity one = new Quantity(oneValue, unit);
        Quantity other = new Quantity(anotherValue, unit);

        Quantity expected = new Quantity(expectedValue, unit);
        Quantity actual = one.add(other);

        assertEquals(expected, actual, message);
    }

    static List<Arguments> add() {
        return List.of(
              arguments("1個+2個=3個", 1, 2, 3, Unit.PIECE),
              arguments("2箱+3箱=5箱", 2, 3, 5, Unit.box(5)),
              arguments("3カートン+2カートン=5カートン", 3, 2, 5, Unit.carton(10, Unit.PIECE))
        );
    }

    @DisplayName("異なる単位の加算で例外")
    @ParameterizedTest
    @MethodSource
    void addDifferentUnit(String message, Unit unit, Unit anotherUnit) {
        Quantity quantity = new Quantity(1, unit);
        Quantity anotherQuantity = new Quantity(1, anotherUnit);

        assertThrows(IllegalArgumentException.class, () -> quantity.add(anotherQuantity));
    }

    static List<Arguments> addDifferentUnit() {
        return List.of(
              arguments("ピースと箱", Unit.PIECE, Unit.box(10)),
              arguments("大箱と子箱", Unit.box(20), Unit.box(10)),
              arguments("箱とカートン", Unit.box(10), Unit.carton(4, Unit.box(10))),
              arguments("小さいカートンと大きなカートン", Unit.carton(4, Unit.box(20)), Unit.carton(4, Unit.box(10)))
        );
    }

    @Test
    @DisplayName("加算オーバーフロー")
    void addPieceOverflow() {
        Quantity max = new Quantity(Integer.MAX_VALUE, Unit.PIECE);
        Quantity other = new Quantity(1, Unit.PIECE);

        assertThrows(ArithmeticException.class, () -> max.add(other));
    }

    @DisplayName("引き算")
    @ParameterizedTest
    @MethodSource
    void subtract(String message, int oneValue, int anotherValue, int expectedValue, Unit unit) {
        Quantity one = new Quantity(oneValue, unit);
        Quantity other = new Quantity(anotherValue, unit);

        Quantity expected = new Quantity(expectedValue, unit);
        Quantity actual = one.subtract(other);

        assertEquals(expected, actual, message);
    }

    static List<Arguments> subtract() {
        return List.of(
              arguments("2個-1個=1個", 2, 1, 1, Unit.PIECE),
              arguments("3箱-1箱=2箱", 3, 1, 2, Unit.box(5)),
              arguments("2カートン-2カートン=0", 2, 2, 0, Unit.carton(10, Unit.PIECE))
        );
    }

    @DisplayName("異なる単位の減算で例外")
    @ParameterizedTest
    @MethodSource
    void subtractDifferentUnit(String message, Unit unit, Unit anotherUnit) {
        Quantity quantity = new Quantity(1, unit);
        Quantity anotherQuantity = new Quantity(1, anotherUnit);

        assertThrows(IllegalArgumentException.class, () -> quantity.subtract(anotherQuantity));
    }

    static List<Arguments> subtractDifferentUnit() {
        return List.of(
              arguments("ピースと箱", Unit.PIECE, Unit.box(10)),
              arguments("大箱と子箱", Unit.box(20), Unit.box(10)),
              arguments("箱とカートン", Unit.box(10), Unit.carton(4, Unit.box(10))),
              arguments("小さいカートンと大きなカートン", Unit.carton(4, Unit.box(20)), Unit.carton(4, Unit.box(10)))
        );
    }

    @Test
    @DisplayName("減算オーバーフロー")
    void subtractPieceOverflow() {
        Quantity max = new Quantity(Integer.MIN_VALUE, Unit.PIECE);
        Quantity other = new Quantity(1, Unit.PIECE);

        assertThrows(ArithmeticException.class, () -> max.subtract(other));
    }

    @DisplayName("乗算")
    @ParameterizedTest
    @MethodSource
    void multiply(String message, int quantityValue, Unit unit, int multiplier, int expectedValue) {
        Quantity quantity = new Quantity(quantityValue, unit);
        Quantity actual = quantity.multiply(multiplier);

        assertEquals(new Quantity(expectedValue, unit), actual, message);
    }

    static List<Arguments> multiply() {
        return List.of(
              arguments("ピース", 2, Unit.PIECE, 3, 6),
              arguments("箱", 2, Unit.box(10), 3, 6),
              arguments("カートン", 2, Unit.carton(10,Unit.box(5)), 3, 6)
        );
    }

    @Test
    @DisplayName("乗算オーバーフロー")
    void multiplyPieceOverflow() {
        Quantity quantity = new Quantity(Integer.MAX_VALUE, Unit.PIECE);
        assertThrows(ArithmeticException.class, () -> quantity.multiply(2));
    }
}