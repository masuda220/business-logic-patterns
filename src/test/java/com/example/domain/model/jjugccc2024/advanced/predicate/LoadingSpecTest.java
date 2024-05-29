package com.example.domain.model.jjugccc2024.advanced.predicate;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class LoadingSpecTest {

    @ParameterizedTest
    @MethodSource("必要条件と期待する結果")
    void 強化型コンテナが必要条件を満たすかの判定(Predicate<Container> 条件, boolean 結果) {
        Container 強化型コンテナ = new Container(Set.of(ContainerFeature.構造強化型));
        assertEquals(結果, 条件.test(強化型コンテナ));
    }

    static LoadingSpec 強化型が必要 = new LoadingSpec(ContainerFeature.構造強化型);
    static LoadingSpec 換気設備付きが必要 = new LoadingSpec(ContainerFeature.通気設備付き);

    static Stream<Arguments> 必要条件と期待する結果() {
        return Stream.of(
             arguments(強化型が必要, true),
             arguments(換気設備付きが必要, false),
             arguments(強化型が必要.negate(), false),
             arguments(強化型が必要.or(換気設備付きが必要), true),
             arguments(強化型が必要.and(換気設備付きが必要), false)
        );
    }
}