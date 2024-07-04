package com.example.domain.model.jjugccc2024.advanced.routing;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

class RouteMapFactory {
    static RouteMap 隣接リストの構築(Set<Path> 経路の集合) {

        List<Path> 逆方向の経路の集合 = 経路の集合.stream()
                .map(Path::始点と終点の入れ替え).toList();

        Set<Path> 全ての経路 = Stream.concat(経路の集合.stream(), 逆方向の経路の集合.stream()).collect(toSet());

        Map<Place, List<Place>> 隣接リスト = 全ての経路.stream()
                .collect(groupingBy(Path::始点, mapping(Path::終点, toList())));

        return new RouteMap(隣接リスト, 全ての経路);
    }
}
