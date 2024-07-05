package com.example.domain.model.jjugccc2024.advanced.routing;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 * 地点の集合
 */
class PlaceList {
    List<Place> 地点の一覧;

    PlaceList(List<Place> 地点の一覧) {
        this.地点の一覧 = 地点の一覧;
    }

    static PlaceList of(List<Place> 地点の一覧) {
        return new PlaceList(地点の一覧);
    }

    List<Place> 除外(Set<Place> 除外する地点) {
        return 地点の一覧.stream()
                .filter(Predicate.not(除外する地点::contains))
                .toList();
    }
}
