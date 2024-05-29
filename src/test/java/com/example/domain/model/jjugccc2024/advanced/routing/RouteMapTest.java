package com.example.domain.model.jjugccc2024.advanced.routing;

import org.junit.jupiter.api.Test;

import java.util.Set;

class RouteMapTest {

    Place 東京 = new Place("東京");
    Place 新横浜 = new Place("新横浜");
    Place 長野 = new Place("長野");

    Set<Path> 経路の集合 = Set.of(
            new Path(東京, 新横浜),
            new Path(東京, 長野)
    );

    @Test
    void create() {
        RouteMap routeMap = RouteMapFactory.createRouteMap(経路の集合);
        System.out.println(routeMap);
    }
}