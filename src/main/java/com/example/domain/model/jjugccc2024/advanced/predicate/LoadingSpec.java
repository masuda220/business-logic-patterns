package com.example.domain.model.jjugccc2024.advanced.predicate;

import java.util.function.Predicate;

import static com.example.domain.model.jjugccc2024.advanced.predicate.ContainerFeature.*;

/**
 * 積載条件
 */
class LoadingSpec implements Predicate<Container> {

    ContainerFeature 必要なコンテナ機能;

    /**
     * 定義済の必要条件
     */
    static Predicate<Container> 条件_強化 = new LoadingSpec(構造強化型);
    static Predicate<Container> 条件_換気 = new LoadingSpec(通気設備付き);
    static Predicate<Container> 条件_強化かつ換気 = 条件_強化.and(条件_換気);
    static Predicate<Container> 条件_標準 = (条件_強化.negate()).and(条件_換気.negate());

    LoadingSpec(ContainerFeature 必要なコンテナ機能) {
        this.必要なコンテナ機能 = 必要なコンテナ機能;
    }

    /**
     * 判定
     *
     * 引数で与えらえたコンテナが、条件を満たすか判断する
     */
    @Override
    public boolean test(Container コンテナ) {
        return コンテナ.満たす(必要なコンテナ機能);
    }
}