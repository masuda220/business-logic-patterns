package com.example.domain.model.jjugccc2024.advanced.routing;

import com.example.domain.model.jjugccc2024.advanced.routing.place.Place;
import com.example.domain.model.jjugccc2024.advanced.routing.place.PlaceList;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 地点ごとの距離
 */
class Distances {
    Map<Place, Integer> 地点ごとの距離;

    Distances(Map<Place, Integer> 地点ごとの距離) {
        this.地点ごとの距離 = 地点ごとの距離;
    }

    int 最も遠い地点への距離() {
        return Collections.max(地点ごとの距離.values());
    }


    PlaceList 最も遠い地点のリスト() {
        int 最も遠い地点までの距離 = Collections.max(地点ごとの距離.values());
        List<Place> 地点のリスト = 地点ごとの距離.entrySet().stream()
                .filter(地点と接続数 -> 地点と接続数.getValue() == 最も遠い地点までの距離)
                .map(Entry::getKey)
                .toList();
        return new PlaceList(地点のリスト);
    }

}
