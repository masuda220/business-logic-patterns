package com.example.domain.model.jjugccc2024.advanced.routing.routes;

import com.example.domain.model.jjugccc2024.advanced.routing.place.Place;
import com.example.domain.model.jjugccc2024.advanced.routing.place.PlaceList;

import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

/**
 * 路線図
 * <p>
 * 地点と隣接地点リストのマップ
 */
public class RouteMap {
    final PathList 経路一覧;
    final Map<Place, Set<Place>> 隣接リストのマップ;

    RouteMap(PathList 経路一覧, Map<Place, Set<Place>> 隣接リストのマップ) {
        this.経路一覧 = 経路一覧;
        this.隣接リストのマップ = 隣接リストのマップ;
    }

    public PlaceList 隣接リスト(Place 地点) {
        return PlaceList.of(隣接リストのマップ.get(地点));
    }

    public int 地点間の距離(Place 起点, Place 終点) {
        return 経路一覧.地点間の距離(起点, 終点);
    }
    public Connections 接続数() {
        Map<Place, Integer> 地点ごとの接続数 = 隣接リストのマップ.entrySet().stream()
                .collect(toMap(Map.Entry::getKey, e -> e.getValue().size()));
        return new Connections(地点ごとの接続数);
    }


    public static RouteMap 隣接リストの構築(PathList 経路リスト) {

        PathList 逆方向の経路の一覧 = 経路リスト.逆方向の経路一覧();
        PathList 全ての経路 = 経路リスト.合成(逆方向の経路の一覧);
        Map<Place, Set<Place>> 隣接リスト =
            全ての経路.経路の集合.stream()
                    .collect(groupingBy(Path::始点, mapping(Path::終点, toSet())));

        return new RouteMap(経路リスト, 隣接リスト);
    }

    @Override
    public String toString() {
        return "RouteMap{" +
                "routeMap=" + 隣接リストのマップ +
                '}';
    }
}