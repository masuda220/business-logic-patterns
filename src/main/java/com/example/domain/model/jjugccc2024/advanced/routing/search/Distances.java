package com.example.domain.model.jjugccc2024.advanced.routing.search;

import com.example.domain.model.jjugccc2024.advanced.routing.place.Place;
import com.example.domain.model.jjugccc2024.advanced.routing.place.PlaceList;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 各地点への距離
 */
class Distances {
    Map<Place, Integer> 地点ごとの距離 = new HashMap<>();

    void 追加(Place 地点, int 距離) {
        地点ごとの距離.put(地点, 距離);
    }

    PlaceList 探索済の地点() {
        return PlaceList.of(地点ごとの距離.keySet());
    }

    int 出発地からの距離(Place 対象地点) {
        return 地点ごとの距離.get(対象地点);
    }

    int 最も遠い地点への距離() {
        return Collections.max(地点ごとの距離.values());
    }

    PlaceList 最も遠い地点のリスト() {
        int 最も遠い地点までの距離 = Collections.max(地点ごとの距離.values());
        Set<Place> 地点の集合 = 地点ごとの距離.entrySet().stream()
                .filter(地点と接続数 -> 地点と接続数.getValue() == 最も遠い地点までの距離)
                .map(Entry::getKey)
                .collect(Collectors.toSet());
        return PlaceList.of(地点の集合);
    }

    @Override
    public String toString() {
        return "Distances{" +
                "地点ごとの距離=" + 地点ごとの距離 +
                '}';
    }
}
