package com.example.domain.model.jjugccc2024.advanced.routing;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.*;

/**
 * 経路
 */
class RouteMap {
    Map<Place, List<Place>> 隣接リストのマップ;
    Set<Path> 経路の集合;

    RouteMap(Map<Place, List<Place>> 隣接リストのマップ, Set<Path> 経路の集合) {
        this.隣接リストのマップ = 隣接リストのマップ;
        this.経路の集合 = 経路の集合;
    }

    int 地点間の距離(Place 現在地, Place 隣接地) {
        Path result = 経路の集合.stream()
                .filter(経路 -> 経路.一致(現在地, 隣接地 )).findFirst().orElseThrow();
        return result.距離();
    }

    PlaceList 隣接リスト(Place 地点) {
        return PlaceList.of(隣接リストのマップ.get(地点));
    }

    Map<Integer, List<Place>> 接続数別グルーピング() {
        return 隣接リストのマップ.entrySet().stream()
                .collect(groupingBy(entry -> entry.getValue().size(), mapping(Map.Entry::getKey, toList())));
    }

    @Override
    public String toString() {
        return "RouteMap{" +
                "routeMap=" + 隣接リストのマップ +
                '}';
    }
}
