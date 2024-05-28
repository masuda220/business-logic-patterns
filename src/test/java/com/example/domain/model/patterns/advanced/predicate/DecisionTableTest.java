package com.example.domain.model.patterns.advanced.predicate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DecisionTableTest {

    static Container 強化コンテナ = new Container(Set.of(ContainerFeature.構造強化型));
    static Container 通気コンテナ = new Container(Set.of(ContainerFeature.通気設備付き));
    static Container 強化かつ通気コンテナ = new Container(Set.of(ContainerFeature.構造強化型, ContainerFeature.通気設備付き));
    static Container 標準コンテナ = new Container(Set.of());

    @DisplayName("決定表によるテスト")
    @ParameterizedTest
    @MethodSource("決定表")
    void 決定表テスト(CargoType 貨物種類, Container コンテナ, boolean 結果) {
        assertEquals(結果, 貨物種類.格納できる(コンテナ));
    }

    static Stream<Arguments> 決定表() {
        return Stream.of(
                // 爆発物
                arguments(CargoType.爆発性, 強化コンテナ, true),
                arguments(CargoType.爆発性, 通気コンテナ, false),
                arguments(CargoType.爆発性, 強化かつ通気コンテナ, true),
                arguments(CargoType.爆発性, 標準コンテナ, false),
                // 揮発性
                arguments(CargoType.揮発性, 強化コンテナ, false),
                arguments(CargoType.揮発性, 通気コンテナ, true),
                arguments(CargoType.揮発性, 強化かつ通気コンテナ, true),
                arguments(CargoType.揮発性, 標準コンテナ, false),
                // 爆発性かつ揮発性
                arguments(CargoType.爆発性かつ揮発性, 強化コンテナ, false),
                arguments(CargoType.爆発性かつ揮発性, 通気コンテナ, false),
                arguments(CargoType.爆発性かつ揮発性, 強化かつ通気コンテナ, true),
                arguments(CargoType.爆発性かつ揮発性, 標準コンテナ, false),
                // 一般品
                arguments(CargoType.一般品, 強化コンテナ, false),
                arguments(CargoType.一般品, 通気コンテナ, false),
                arguments(CargoType.一般品, 強化かつ通気コンテナ, false),
                arguments(CargoType.一般品, 標準コンテナ, true)
        );
    }
}