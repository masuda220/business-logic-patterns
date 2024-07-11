package com.example.domain.model.jjugccc2024.advanced.routing.search;

import com.example.domain.model.jjugccc2024.advanced.routing.place.Place;
import com.example.domain.model.jjugccc2024.advanced.routing.place.PlaceList;
import com.example.domain.model.jjugccc2024.advanced.routing.routes.RouteMap;

/**
 * 幅優先探索
 */
class DistancesBuilder {
    // 参照情報（不変）
    final Place 出発地;
    final RouteMap 経路マップ; // 隣接リストのマップ

    // 生成情報（可変）
    Distances 各地点までの距離 = new Distances();
    PlaceQueue 探索地点のキュー = new PlaceQueue();

    DistancesBuilder(Place 出発地, RouteMap 経路マップ) {
        this.出発地 = 出発地;
        this.経路マップ = 経路マップ;
    }

    void 幅優先で探索() {
        出発地を使って探索地点のキューと探索結果を初期化();
        while (探索地点のキュー.空でない()) {
            Place 現在地 = 探索地点のキュー.取り出し();
            未探索の地点の一覧(現在地).toSet()
                    .forEach(隣接地 -> 出発地から隣接地までの距離を追加(現在地, 隣接地));
        }
    }

    private void 出発地を使って探索地点のキューと探索結果を初期化() {
        各地点までの距離.追加(出発地, 0); // 出発地から出発地の距離はゼロ
        探索地点のキュー.追加(出発地); // 探索の開始地点
    }

    private PlaceList 未探索の地点の一覧(Place 現在地) {
        PlaceList 隣接地のリスト = 経路マップ.隣接地点のリスト(現在地);
        PlaceList 除外する地点 = 各地点までの距離.探索済の地点();
        return 隣接地のリスト.除外(除外する地点);
    }

    private void 出発地から隣接地までの距離を追加(Place 現在地, Place 隣接地) {
        int 出発地から現在地までの距離 = 各地点までの距離.出発地からの距離(現在地);
        int 現在地から隣接地への距離 = 経路マップ.地点間の距離(現在地, 隣接地);
        各地点までの距離.追加(隣接地, 出発地から現在地までの距離 + 現在地から隣接地への距離);
        探索地点のキュー.追加(隣接地);
    }

    Distances 探索結果() {
        return 各地点までの距離;
    }

    @Override
    public String toString() {
        return "ShortestDistanceMap{" +
                "始点=" + 出発地 +
                ", 各地点までの距離=" + 各地点までの距離 +
                '}';
    }
}
