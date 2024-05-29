package com.example.domain.model.jjugccc2024.advanced.predicate;

import java.util.function.Predicate;

import static com.example.domain.model.jjugccc2024.advanced.predicate.LoadingSpec.*;

/**
 * 貨物特性
 *
 * 特性ごとに必要とするコンテナ機能を定義
 */
public enum CargoType {
    爆発性(条件_強化),
    揮発性(条件_換気),
    爆発性かつ揮発性(条件_強化かつ換気),
    一般品(条件_標準);

    final Predicate<Container> 必要条件;

    CargoType(Predicate<Container> 必要条件) {
        this.必要条件 = 必要条件;
    }

    boolean 格納できる(Container コンテナ) {
        return 必要条件.test(コンテナ);
    }
}
