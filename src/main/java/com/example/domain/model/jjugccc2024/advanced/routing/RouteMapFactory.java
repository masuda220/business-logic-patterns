package com.example.domain.model.jjugccc2024.advanced.routing;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

class RouteMapFactory {
    static RouteMap 隣接リストの構築(Set<PathWithDistance> 経路の集合) {

        List<PathWithDistance> 逆方向の経路の集合 = 経路の集合.stream()
                .map(PathWithDistance::逆転).toList();

        Map<Place, List<Place>> 隣接リスト =
                Stream.concat(経路の集合.stream(), 逆方向の経路の集合.stream())
                .collect(groupingBy(PathWithDistance::出発地, mapping(PathWithDistance::到着地, toList())));

        Map<Path, Integer> パス間の距離 =
                Stream.concat(経路の集合.stream(), 逆方向の経路の集合.stream())
                        .collect(toMap(PathWithDistance::パス, PathWithDistance::距離));

        return new RouteMap(隣接リスト, パス間の距離);
    }
}
