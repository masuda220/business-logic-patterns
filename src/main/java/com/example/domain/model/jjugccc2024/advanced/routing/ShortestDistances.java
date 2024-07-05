package com.example.domain.model.jjugccc2024.advanced.routing;

import java.util.Comparator;
import java.util.Map;

/**
 * 出発地から各地点までの最短距離
 */
class ShortestDistances {

    Place 出発地;
    Map<Place, Integer> 各地点までの最短距離;

    ShortestDistances(Place 出発地, Map<Place, Integer> 各地点までの最短距離) {
        this.出発地 = 出発地;
        this.各地点までの最短距離 = 各地点までの最短距離;
    }

    // todo 抽象化
    Path 出発地から最も遠い地点への距離() {
        Map.Entry<Place, Integer> 地点と距離 =
                各地点までの最短距離.entrySet().stream()
                        .max(Comparator.comparingInt(Map.Entry::getValue)).orElseThrow();
        return new Path(出発地, 地点と距離.getKey(), 地点と距離.getValue());
    }

}
