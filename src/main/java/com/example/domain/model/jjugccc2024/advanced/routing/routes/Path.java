package com.example.domain.model.jjugccc2024.advanced.routing.routes;

import com.example.domain.model.jjugccc2024.advanced.routing.place.Place;

/**
 * 経路
 */
public record Path(Place 始点, Place 終点, int 距離) {

    boolean 一致(Place 始点, Place 終点) {
        return this.始点.同一地点(始点) && this.終点.同一地点(終点);
    }

    Path 始点と終点の入れ替え() {
        return new Path(終点, 始点, this.距離);
    }
}
