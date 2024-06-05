package com.example.domain.model.jjugccc2024.advanced.proportion;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class SharePieByPointTest {

    @ParameterizedTest
    @MethodSource("配分")
    void 比例配分(SharePieByPoint 構成比率, int 配分する送料, SharePieByAmount 期待する結果) {
        SharePieByAmount 金額構成 = 構成比率.比例配分(new Amount(12345));
        assertTrue(期待する結果.同じ金額構成(金額構成));
    }

    static Stream<Arguments> 配分() {
        return Stream.of(
            arguments(
                SharePieByPoint.of(SharePie.値の大きい順で構築(
                    List.of(
                        Share.of("A社", 1999),
                        Share.of("B社", 2998),
                        Share.of("C社", 5003)))),
                12345,
                SharePieByAmount.of(SharePie.値の大きい順で構築(
                    List.of(
                        Share.of("A社", 2467),
                        Share.of("B社", 3701),
                        Share.of("C社", 6177))))
            )
        );
    }
}