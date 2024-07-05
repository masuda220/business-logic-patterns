package com.example.domain.model.jjugccc2024.advanced.routing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 経路の幅優先探索
 */
class ShortestDistancesBuilder {
    Place 出発地;
    Map<Place, Integer> 各地点までの最短距離;
    PathList 経路一覧;
    RouteMap 経路マップ; // 隣接リストのマップ

    private ShortestDistancesBuilder(Place 出発地,
                                     Map<Place, Integer> 各地点までの最短距離,
                                     PathList 経路一覧,
                                     RouteMap 経路マップ) {
        this.出発地 = 出発地;
        this.各地点までの最短距離 = 各地点までの最短距離;
        this.経路一覧 = 経路一覧;
        this.経路マップ = 経路マップ;
    }

    // todo ロジックのさらなる抽象化　生成と問合せのクラスを分ける？
    void 幅優先で探索() {
        SearchQueue 探索キュー = new SearchQueue();
        探索キュー.追加(出発地); // 探索の開始地点

        while (探索キュー.空でない()) {
            Place 現在地 = 探索キュー.取り出し();
            Set<Place> 除外する地点 = 各地点までの最短距離.keySet(); // 探索済の地点
            List<Place> 探索地に隣接する未探索の地点 = 経路マップ.隣接リスト(現在地).除外(除外する地点);
            探索地に隣接する未探索の地点.forEach(隣接地 -> {
                        出発地から隣接地までの最短距離の更新(現在地, 隣接地, 経路マップ);
                        探索キュー.追加(隣接地);
                    }
            );
        }
    }

    private void 出発地から隣接地までの最短距離の更新(Place 現在地, Place 隣接地, RouteMap 経路マップ) {
        int 出発地から現在地までの距離 = 各地点までの最短距離.get(現在地);
        int 現在地から隣接地への距離 = 経路一覧.地点間の距離(現在地, 隣接地);
        各地点までの最短距離.put(隣接地, 出発地から現在地までの距離 + 現在地から隣接地への距離);
    }

    ShortestDistances 探索結果() {
        return new ShortestDistances(出発地, 各地点までの最短距離);
    }

    static ShortestDistancesBuilder 初期化(Place 出発地, PathList 経路一覧, RouteMap 経路マップ) {
        Map<Place, Integer> 各地点までの最短距離 = new HashMap<>();
        各地点までの最短距離.put(出発地, 0); // 出発地から出発地の距離はゼロ

        return new ShortestDistancesBuilder(出発地, 各地点までの最短距離, 経路一覧, 経路マップ);
    }

    @Override
    public String toString() {
        return "ShortestDistanceMap{" +
                "始点=" + 出発地 +
                ", 各地点までの最短距離=" + 各地点までの最短距離 +
                '}';
    }
}
