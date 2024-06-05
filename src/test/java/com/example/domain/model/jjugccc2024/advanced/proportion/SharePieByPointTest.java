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
    void 比例配分(SharePieByPoint 構成比率, int 配分対象, SharePieByAmount 期待する結果) {
        SharePieByAmount 金額構成 = 構成比率.比例配分(new Amount(配分対象));
        System.out.println(金額構成);
        System.out.println(期待する結果);
        assertTrue(期待する結果.同じ金額構成(金額構成));

    }

    static Stream<Arguments> 配分() {
        return Stream.of(
            arguments(
                SharePieByPoint.of(SharePie.値の大きい順で構築(
                    List.of(
                        Share.of("A社", 1_999),
                        Share.of("B社", 2_998),
                        Share.of("C社", 5_003)))),
                134675,
                SharePieByAmount.of(SharePie.値の大きい順で構築(
                    List.of(
                        Share.of("A社", 26_921),
                        Share.of("B社", 40_375),
                        Share.of("C社", 67_379))))
            )
        );
    }
}