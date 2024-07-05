package com.example.domain.model.jjugccc2024.advanced.routing;

/**
 * 地点
 */
record Place(String 名称) {
    boolean 同一地点(Place 別の地点) {
        return this.名称.equals(別の地点.名称);
    }
}
