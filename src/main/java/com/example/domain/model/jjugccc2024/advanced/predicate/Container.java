package com.example.domain.model.jjugccc2024.advanced.predicate;

import java.util.Set;

/**
 * コンテナ
 */
class Container {
    Set<ContainerFeature> コンテナ機能;

    private Container() {} // 提供機能を指定しない生成は不可

    Container(Set<ContainerFeature> コンテナ機能) {
        this.コンテナ機能 = コンテナ機能;
    }

    boolean 満たす(ContainerFeature 要求機能) {
        return コンテナ機能.contains(要求機能);
    }
}
