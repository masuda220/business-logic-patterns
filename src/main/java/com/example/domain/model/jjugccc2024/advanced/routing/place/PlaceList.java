package com.example.domain.model.jjugccc2024.advanced.routing.place;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 * 地点の集合
 */
public class PlaceList {
    List<Place> 地点の一覧;

    public PlaceList(List<Place> 地点の一覧) {
        this.地点の一覧 = 地点の一覧;
    }

    public static PlaceList of(List<Place> 地点の一覧) {
        return new PlaceList(地点の一覧);
    }

    public boolean 地点を含む(Place 対象地点) {
        return 地点の一覧.contains(対象地点);
    }

    public int 地点数() {
        return 地点の一覧.size();
    }

    public List<Place> 除外(Set<Place> 除外する地点) {
        return 地点の一覧.stream()
                .filter(Predicate.not(除外する地点::contains))
                .toList();
    }
}
