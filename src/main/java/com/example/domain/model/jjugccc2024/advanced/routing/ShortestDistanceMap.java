package com.example.domain.model.jjugccc2024.advanced.routing;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 出発地から各地点までの最短距離
 */
class ShortestDistanceMap {
    Place 出発地;
    Map<Place, Integer> 各地点までの最短距離;
    Map<Path, Integer> 地点間の距離;

    private ShortestDistanceMap(Place 出発地,
                                Map<Place, Integer> 各地点までの最短距離,
                                Map<Path, Integer> 地点間の距離) {
        this.出発地 = 出発地;
        this.各地点までの最短距離 = 各地点までの最短距離;
        this.地点間の距離 = 地点間の距離;
    }

    void 出発地から隣接地までの最短距離の更新(Place 現在地, Place 隣接地) {
        int 出発地から現在地までの距離 = 各地点までの最短距離.get(現在地) ;
        int 現在地から隣接地への距離 = 地点間の距離.get(new Path(現在地, 隣接地));
        各地点までの最短距離.put(隣接地, 出発地から現在地までの距離 + 現在地から隣接地への距離);
    }

    boolean 未探索(Place 地点) {
        return !各地点までの最短距離.containsKey(地点);
    }

    PathWithDistance 出発地点から最も遠い地点への距離() {
        Map.Entry<Place,Integer> 地点と距離 =
                各地点までの最短距離.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue)).orElseThrow();
        return new PathWithDistance(new Path(出発地, 地点と距離.getKey()), 地点と距離.getValue());
    }

    static ShortestDistanceMap 初期化(Place 出発地, Map<Path, Integer> パスの長さのマップ) {
        Map<Place, Integer> 各地点までの最短距離 = new HashMap<>();
        各地点までの最短距離.put(出発地, 0); // 出発地から出発地の距離はゼロ

        return new ShortestDistanceMap(出発地, 各地点までの最短距離, パスの長さのマップ);
    }

    @Override
    public String toString() {
        return "ShortestDistanceMap{" +
                "出発地=" + 出発地 +
                ", 各地点までの最短距離=" + 各地点までの最短距離 +
//                ", パスの長さ=" + パスの長さ +
                '}';
    }
}
