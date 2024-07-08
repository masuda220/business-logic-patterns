package com.example.domain.model.jjugccc2024.advanced.routing.place;

/**
 * 地点
 */
public record Place(String 名称) {
    public boolean 同一地点(Place 別の地点) {
        return this.名称.equals(別の地点.名称);
    }
}
