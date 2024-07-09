package com.example.domain.model.jjugccc2024.advanced.routing.place;

import java.util.*;
import java.util.Map.Entry;

/**
 * 地点ごとの距離
 */
// TODO 出発地は、距離０で検出可能
public class Distances {
    Map<Place, Integer> 地点ごとの距離 = new HashMap<>();

    public void 追加(Place 地点, int 距離) {
        地点ごとの距離.put(地点, 距離);
    }

    public Set<Place> 探索済の地点() {
        return 地点ごとの距離.keySet();
    }

    public int 出発地からの距離(Place 対象地点) {
        return 地点ごとの距離.get(対象地点);
    }

    public int 最も遠い地点への距離() {
        return Collections.max(地点ごとの距離.values());
    }

    public PlaceList 最も遠い地点のリスト() {
        int 最も遠い地点までの距離 = Collections.max(地点ごとの距離.values());
        List<Place> 地点のリスト = 地点ごとの距離.entrySet().stream()
                .filter(地点と接続数 -> 地点と接続数.getValue() == 最も遠い地点までの距離)
                .map(Entry::getKey)
                .toList();
        return new PlaceList(地点のリスト);
    }

    @Override
    public String toString() {
        return "Distances{" +
                "地点ごとの距離=" + 地点ごとの距離 +
                '}';
    }
}
