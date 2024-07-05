package com.example.domain.model.jjugccc2024.advanced.routing;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

/**
 * 路線図
 *
 * 地点と隣接地点リストのマップ
 */
class RouteMap {
    Map<Place, List<Place>> 隣接リストのマップ;

    RouteMap(Map<Place, List<Place>> 隣接リストのマップ) {
        this.隣接リストのマップ = 隣接リストのマップ;
    }

    PlaceList 隣接リスト(Place 地点) {
        return PlaceList.of(隣接リストのマップ.get(地点));
    }

    Map<Integer, List<Place>> 接続数別グルーピング() {
        return 隣接リストのマップ.entrySet().stream()
                .collect(groupingBy(entry -> entry.getValue().size(), mapping(Map.Entry::getKey, toList())));
    }

    static RouteMap 隣接リストの構築(PathList 経路一覧) {

        PathList 逆方向の経路の一覧 = 経路一覧.逆方向の経路一覧();
        PathList 全ての経路 =経路一覧.合成(逆方向の経路の一覧);
        Map<Place, List<Place>> 隣接リスト = 全ての経路.隣接リスト();

        return new RouteMap(隣接リスト);
    }

    @Override
    public String toString() {
        return "RouteMap{" +
                "routeMap=" + 隣接リストのマップ +
                '}';
    }
}