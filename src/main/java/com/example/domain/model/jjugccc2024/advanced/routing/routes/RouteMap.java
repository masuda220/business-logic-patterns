package com.example.domain.model.jjugccc2024.advanced.routing.routes;

import com.example.domain.model.jjugccc2024.advanced.routing.place.Connections;
import com.example.domain.model.jjugccc2024.advanced.routing.place.Place;
import com.example.domain.model.jjugccc2024.advanced.routing.place.PlaceList;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

/**
 * 路線図
 * <p>
 * 地点と隣接地点リストのマップ
 */
public class RouteMap {
    public Map<Place, List<Place>> 隣接リストのマップ;

    RouteMap(Map<Place, List<Place>> 隣接リストのマップ) {
        this.隣接リストのマップ = 隣接リストのマップ;
    }

    public PlaceList 隣接リスト(Place 地点) {
        return PlaceList.of(隣接リストのマップ.get(地点));
    }

    public Connections 接続数() {
        Map<Place, Integer> 地点ごとの接続数 = 隣接リストのマップ.entrySet().stream()
                .collect(toMap(Map.Entry::getKey, e -> e.getValue().size()));
        return new Connections(地点ごとの接続数);
    }


    public static RouteMap 隣接リストの構築(PathList 経路リスト) {

        PathList 逆方向の経路の一覧 = 経路リスト.逆方向の経路一覧();
        PathList 全ての経路 = 経路リスト.合成(逆方向の経路の一覧);
        Map<Place, List<Place>> 隣接リスト =
            全ての経路.経路の集合.stream()
                    .collect(groupingBy(Path::始点, mapping(Path::終点, toList())));

        return new RouteMap(隣接リスト);
    }

    @Override
    public String toString() {
        return "RouteMap{" +
                "routeMap=" + 隣接リストのマップ +
                '}';
    }
}