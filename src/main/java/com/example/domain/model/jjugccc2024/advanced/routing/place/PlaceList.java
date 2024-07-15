package com.example.domain.model.jjugccc2024.advanced.routing.place;

import java.util.Set;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toSet;

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
        return 地点の一覧.stream()
                .filter(除外する地点の一覧::地点を含まない)
                .collect(collectingAndThen(toSet(), PlaceList::of));
    }

    public Set<Place> 地点の一覧() {
        return 地点の一覧;
    }

    public PlaceList 地点数が多い方(PlaceList 比較対象) {
        if(地点数() > 比較対象.地点数()) return this;
        return 比較対象;
    }
}
