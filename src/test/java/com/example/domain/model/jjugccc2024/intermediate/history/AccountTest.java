package com.example.domain.model.jjugccc2024.intermediate.history;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class AccountTest {

    static final EventHistory 入出金履歴 = EventHistory.of(
            List.of(
                    AccountEvent.入金(LocalDate.parse("2021-04-05"), Amount.of(1_000)),
                    AccountEvent.入金(LocalDate.parse("2021-04-10"), Amount.of(3_000)),
                    AccountEvent.入金(LocalDate.parse("2021-04-20"), Amount.of(1_000)),
                    //
                    AccountEvent.出金(LocalDate.parse("2021-04-05"), Amount.of(1_000)),
                    AccountEvent.出金(LocalDate.parse("2021-04-11"), Amount.of(2_000)),
                    AccountEvent.出金(LocalDate.parse("2021-04-19"), Amount.of(500))
            )
    );

    static final Account 口座 = Account.生成(入出金履歴);

    static Stream<Arguments> 日付と残高() {
        return Stream.of(
                arguments(LocalDate.parse("2021-04-04"), Amount.of(0)),
                arguments(LocalDate.parse("2021-04-05"), Amount.of(0)),
                arguments(LocalDate.parse("2021-04-10"), Amount.of(3_000)),
                arguments(LocalDate.parse("2021-04-11"), Amount.of(1_000)),
                arguments(LocalDate.parse("2021-04-19"), Amount.of(500)),
                arguments(LocalDate.parse("2021-04-20"), Amount.of(1_500))

        );
    }

    @ParameterizedTest
    @MethodSource("日付と残高")
    void 残高(LocalDate 日付, Amount 残高) {
        Amount 口座の残高 = 口座.残高(日付);
        assertTrue(残高.同じ(口座の残高), 日付.toString() + 残高 + 口座の残高);
    }
}