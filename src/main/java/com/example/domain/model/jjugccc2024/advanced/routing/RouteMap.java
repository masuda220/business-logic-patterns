package com.example.domain.model.jjugccc2024.advanced.routing;

import java.util.List;
import java.util.Map;

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

    ShortestDistanceMap 各地点への最短距離(Place 出発地) {
        ShortestDistanceMap 出発地から各地点までの最短距離 = ShortestDistanceMap.初期化(出発地, 各地点間の距離);
        出発地から各地点までの最短距離.幅優先で探索(隣接リストのマップ);

        return 出発地から各地点までの最短距離;
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
