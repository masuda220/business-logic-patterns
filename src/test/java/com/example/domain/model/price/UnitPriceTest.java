package com.example.domain.model.price;

import com.example.domain.type.money.Amount;
import com.example.domain.type.quantity.Quantity;
import com.example.domain.type.quantity.unit.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class UnitPriceTest {

    @DisplayName("正常ケース")
    @ParameterizedTest
    @MethodSource
    void multiply(String message, Amount amount, Unit unit, int number, Amount expected) {
        UnitPrice unitPrice = new UnitPrice(amount, unit);
        Quantity quantity = new Quantity(number, unit);
        Amount actual = unitPrice.multiply(quantity);

        assertEquals(expected, actual, message);
    }

    static List<Arguments> multiply() {
        return List.of(
              arguments("1個95円 * 24個 = 2280円", new Amount(95), Unit.PIECE, 24, new Amount(2280)),
              arguments("1箱105円 * 6箱 = 630円", new Amount(105), Unit.box(5), 6, new Amount(630))
        );
    }

    @DisplayName("単位が不一致")
    @ParameterizedTest
    @MethodSource
    void 単位が不一致(String message, Unit ofUnitPrice, Unit ofQuantity) {
        UnitPrice unitPrice = new UnitPrice(new Amount(100), ofUnitPrice);
        Quantity quantity = new Quantity(10, ofQuantity);
        assertThrows(IllegalArgumentException.class, () -> unitPrice.multiply(quantity), message);
    }

    static List<Arguments> 単位が不一致() {
        return List.of(
              arguments("個数単価×箱数", Unit.PIECE, Unit.box(5)),
              arguments("箱単価×個数", Unit.box(5), Unit.PIECE)
        );
    }

}
