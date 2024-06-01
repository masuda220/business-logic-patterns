package com.example.domain.model.jjugccc2024.intermediate.inventory;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class AvailabilityTest {

    static InboundSchedule 入庫予定 = InboundSchedule.from(
            Set.of(
                    Inbound.of(LocalDate.parse("2022-07-01"), 10),
                    Inbound.of(LocalDate.parse("2022-07-04"), 20),
                    Inbound.of(LocalDate.parse("2022-07-10"), 10),
                    Inbound.of(LocalDate.parse("2022-07-11"), 20)
            )
    );

    static OutboundSchedule 出庫予定 = OutboundSchedule.from(
            Set.of(
                    Outbound.of(LocalDate.parse("2022-07-02"), 5),
                    Outbound.of(LocalDate.parse("2022-07-05"), 15),
                    Outbound.of(LocalDate.parse("2022-07-08"), 10),
                    Outbound.of(LocalDate.parse("2022-07-12"), 15)
            )
    );

    static Availability 未来在庫 = Availability.of(入庫予定, 出庫予定);

    @ParameterizedTest
    @MethodSource("出荷可否")
    void 出荷可能(int 希望数量, boolean 出荷可能) {
        assertEquals(出荷可能, 未来在庫.出荷可能(希望数量));
    }

    static Stream<Arguments> 出荷可否() {
        return Stream.of(
          arguments(5, true),
          arguments(15, true)
        );
    }

    @ParameterizedTest
    @MethodSource("出荷可能日")
    void 出荷可能日の判定(int 希望数量, LocalDate 出荷可能日) {
        assertEquals(出荷可能日, 未来在庫.出荷可能日(希望数量));
    }

    static Stream<Arguments> 出荷可能日() {
        return Stream.of(
                arguments(5, LocalDate.parse("2022-07-02")),
                arguments(15, LocalDate.parse("2022-07-12"))
        );
    }

}