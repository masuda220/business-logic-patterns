package com.example.domain.model.jjugccc2024.beginner.range;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DiscountCategoryTest {

    @ParameterizedTest
    @MethodSource("割引パターン")
    void noDiscount(Amount 元金額, Amount 割引結果) {
        Amount 実行結果 = DiscountCategory.割り引く(元金額);
        assertTrue(実行結果.同じ金額(割引結果),元金額.toString());
    }

    static Stream<Arguments> 割引パターン() {
        return Stream.of(
                arguments(Amount.of(1_000), Amount.of(1_000)),
                arguments(Amount.of(3_000), Amount.of(2_910)),
                arguments(Amount.of(6_000), Amount.of(5_700)),
                arguments(Amount.of(11_000), Amount.of(9_900))
        );
    }
}