package com.example.domain.model.jjugccc2024.advanced.routing.routes;

import com.example.domain.model.jjugccc2024.advanced.routing.place.Place;
import com.example.domain.model.jjugccc2024.advanced.routing.place.PlaceList;

import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toSet;

public class RouteMapFactory {
    public static RouteMap 経路図の構築(PathList 経路リスト) {
        PathList 逆方向の経路の一覧 = 経路リスト.逆方向の経路一覧();
        PathList 全ての経路 = 経路リスト.合成(逆方向の経路の一覧);

        Map<Place, Set<Place>> 隣接リスト = 全ての経路.経路の集合.stream()
                        .collect(groupingBy(Path::始点, mapping(Path::終点, toSet())));

        return new RouteMap(経路リスト, 隣接リスト);

    }
}
