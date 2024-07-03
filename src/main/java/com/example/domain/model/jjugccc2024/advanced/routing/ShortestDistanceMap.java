package com.example.domain.model.jjugccc2024.advanced.routing;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Stream;

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

    void 幅優先で探索(Map<Place, List<Place>> 隣接リストのマップ) {
        Queue<Place> 探索地点のキュー = new LinkedList<>();
        探索地点のキュー.add(出発地); // 探索の開始地点

        while (!探索地点のキュー.isEmpty()) {
            Place 現在地 = 探索地点のキュー.remove(); // 先頭を取り出す
            Stream<Place> 探索地に隣接する未探索の地点 = 未探索の隣接地点のリスト(現在地, 隣接リストのマップ);
            探索地に隣接する未探索の地点.forEach(隣接地点 -> {
                        出発地から隣接地までの最短距離の更新(現在地, 隣接地点);
                        探索地点のキュー.add(隣接地点);
                    }
            );
        }
    }

    private Stream<Place> 未探索の隣接地点のリスト(Place 出発地,Map<Place,List<Place>> 隣接リストのマップ) {
        return 隣接リストのマップ.get(出発地).stream()
                .filter(this::未探索);
    }

    private void 出発地から隣接地までの最短距離の更新(Place 現在地, Place 隣接地) {
        int 出発地から現在地までの距離 = 各地点までの最短距離.get(現在地) ;
        int 現在地から隣接地への距離 = 地点間の距離.get(new Path(現在地, 隣接地));
        各地点までの最短距離.put(隣接地, 出発地から現在地までの距離 + 現在地から隣接地への距離);
    }

    private boolean 未探索(Place 地点) {
        return !各地点までの最短距離.containsKey(地点);
    }

    PathWithDistance 出発地から最も遠い地点への距離() {
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
