package com.example.domain.model.jjugccc2024.advanced.routing.place;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 地点の集合
 */
public class PlaceList {
    Set<Place> 地点の一覧;

    private PlaceList(Set<Place> 地点の一覧) {
        this.地点の一覧 = 地点の一覧;
    }

    public static PlaceList of(Set<Place> 地点の一覧) {
        return new PlaceList(地点の一覧);
    }

    public boolean 地点を含む(Place 対象地点) {
        return 地点の一覧.contains(対象地点);
    }

    public boolean 地点を含まない(Place 対象地点) {
        return !地点を含む(対象地点);
    }

    public int 地点数() {
        return 地点の一覧.size();
    }

    public PlaceList 除外(PlaceList 除外する地点の一覧) {
        Set<Place> result = 地点の一覧.stream()
                .filter(除外する地点の一覧::地点を含まない)
                .collect(Collectors.toSet());
        return PlaceList.of(result);
    }

    public Set<Place> toSet() {
        return 地点の一覧;
    }
}
