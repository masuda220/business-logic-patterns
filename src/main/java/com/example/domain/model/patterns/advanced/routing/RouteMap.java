package com.example.domain.model.patterns.advanced.routing;

import java.util.Map;
import java.util.Set;

/**
 * 経路の表現
 */
public class RouteMap {
    // 隣接リスト
    Map<Place, Set<Place>> routeMap;

    RouteMap(Map<Place, Set<Place>> routeMap) {
        this.routeMap = routeMap;
    }

    @Override
    public String toString() {
        return "RouteMap{" +
                "routeMap=" + routeMap +
                '}';
    }
}
