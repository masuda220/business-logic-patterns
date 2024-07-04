package com.example.domain.model.jjugccc2024.advanced.routing;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 出発地から各地点までの最短距離
 */
class ShortestDistanceMap {
    Place 出発地;
    Map<Place, Integer> 各地点までの最短距離;

    private ShortestDistanceMap(Place 出発地, Map<Place, Integer> 各地点までの最短距離
    ) {
        this.出発地 = 出発地;
        this.各地点までの最短距離 = 各地点までの最短距離;
    }

    void 幅優先で探索(RouteMap 経路マップ) {
        SearchQueue 探索地点のキュー = new SearchQueue();
        探索地点のキュー.追加(出発地); // 探索の開始地点

        while (探索地点のキュー.空でない()) {
            Place 現在地 = 探索地点のキュー.取り出し();
            Set<Place> 除外する地点 = 各地点までの最短距離.keySet(); // 探索済の地点
            List<Place> 探索地に隣接する未探索の地点 = 経路マップ.隣接リスト(現在地).除外(除外する地点);
            探索地に隣接する未探索の地点.forEach(隣接地点 -> {
                        出発地から隣接地までの最短距離の更新(現在地, 隣接地点, 経路マップ);
                        探索地点のキュー.追加(隣接地点);
                    }
            );
        }
    }

    private void 出発地から隣接地までの最短距離の更新(Place 現在地, Place 隣接地, RouteMap 経路マップ) {
        int 出発地から現在地までの距離 = 各地点までの最短距離.get(現在地);
        int 現在地から隣接地への距離 = 経路マップ.地点間の距離(現在地, 隣接地);
        各地点までの最短距離.put(隣接地, 出発地から現在地までの距離 + 現在地から隣接地への距離);
    }

    Path 出発地から最も遠い地点への距離() {
        Map.Entry<Place, Integer> 地点と距離 =
                各地点までの最短距離.entrySet().stream()
                        .max(Comparator.comparingInt(Map.Entry::getValue)).orElseThrow();
        return new Path(出発地, 地点と距離.getKey(), 地点と距離.getValue());
    }

    static ShortestDistanceMap 初期化(Place 出発地) {
        Map<Place, Integer> 各地点までの最短距離 = new HashMap<>();
        各地点までの最短距離.put(出発地, 0); // 出発地から出発地の距離はゼロ

        return new ShortestDistanceMap(出発地, 各地点までの最短距離);
    }


    @Override
    public String toString() {
        return "ShortestDistanceMap{" +
                "始点=" + 出発地 +
                ", 各地点までの最短距離=" + 各地点までの最短距離 +
                '}';
    }
}
