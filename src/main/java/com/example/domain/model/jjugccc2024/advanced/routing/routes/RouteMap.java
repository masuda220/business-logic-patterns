package com.example.domain.model.jjugccc2024.advanced.routing.routes;

import com.example.domain.model.jjugccc2024.advanced.routing.place.Place;
import com.example.domain.model.jjugccc2024.advanced.routing.place.PlaceList;

import java.util.Map;
import java.util.Set;

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

    public PlaceList 隣接地点のリスト(Place 地点) {
        return PlaceList.of(隣接リストのマップ.get(地点));
    }

    public int 地点間の距離(Place 起点, Place 終点) {
        return 経路一覧.地点間の距離(起点, 終点);
    }

    public Connections 地点ごとの接続数() {
        return new Connections(隣接リストのマップ);
    }

    @Override
    public String toString() {
        return "RouteMap{" +
                "routeMap=" + 隣接リストのマップ +
                '}';
    }
}