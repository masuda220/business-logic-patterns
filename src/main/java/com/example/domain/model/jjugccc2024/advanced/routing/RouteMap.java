package com.example.domain.model.jjugccc2024.advanced.routing;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;

/**
 * 経路
 */
class RouteMap {
    Map<Place, List<Place>> 隣接リストのマップ;
    Map<Path, Integer> 各地点間の距離;

    RouteMap(Map<Place, List<Place>> 隣接リストのマップ, Map<Path, Integer> 各地点間の距離) {
        this.隣接リストのマップ = 隣接リストのマップ;
        this.各地点間の距離 = 各地点間の距離;
    }

    int 地点間の距離(Place 現在地, Place 隣接地) {
        return 各地点間の距離.get(new Path(現在地, 隣接地));
    }

    List<Place> 隣接リスト(Place 地点, Set<Place> 対象外) {
        return 隣接リストのマップ.get(地点).stream()
                .filter(Predicate.not(対象外::contains))
                .toList();
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
