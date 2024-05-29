package com.example.domain.model.jjugccc2024.advanced.predicate;

import java.util.Set;

/**
 * コンテナ
 */
public class Container {
    Set<ContainerFeature> 提供機能;

    private Container() {} // 提供機能を指定しない生成は不可

    public Container(Set<ContainerFeature> 提供機能) {
        this.提供機能 = 提供機能;
    }

    boolean 満たす(ContainerFeature 要求機能) {
        return 提供機能.contains(要求機能);
    }
}
