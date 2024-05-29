package com.example.domain.model.jjugccc2024.advanced.routing;

import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.*;

class RouteMapFactory {
    static RouteMap createRouteMap(Set<Path> 経路の集合) {
        Map<Place,Set<Place>> 経路隣接リスト = 経路の集合.stream().collect(
                groupingBy(Path::出発地, mapping(Path::到着地, toSet()))
        );
        return new RouteMap(経路隣接リスト);
    }
}
