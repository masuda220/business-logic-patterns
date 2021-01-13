package com.example.domain.model.order;

import com.example.domain.model.price.UnitPrice;
import com.example.domain.type.money.Amount;
import com.example.domain.type.quantity.Quantity;
import com.example.domain.type.quantity.unit.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class OrderLineTest {

    @DisplayName("明細の金額")
    @ParameterizedTest
    @MethodSource
    void amount(String message, int unitPriceValue, Unit unit, int quantityValue, int expected) {
        UnitPrice unitPrice = new UnitPrice(new Amount(unitPriceValue), unit);
        Quantity quantity = new Quantity(quantityValue, unit);
        OrderLine orderLine = new OrderLine(unitPrice, quantity);
        assertEquals(new Amount(expected), orderLine.amount(), message);
    }

    static List<Arguments> amount() {
        return List.of(
          arguments("10円/個×2個", 10, Unit.PIECE, 2, 20),
          arguments("90円/箱×2箱", 90, Unit.box(10), 2, 180),
          arguments("170円/カートン×2カートン", 170, Unit.PIECE, 2, 340)
        );
    }
}