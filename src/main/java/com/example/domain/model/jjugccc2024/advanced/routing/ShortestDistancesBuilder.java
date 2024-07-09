package com.example.domain.model.jjugccc2024.advanced.routing;

import com.example.domain.model.jjugccc2024.advanced.routing.place.Distances;
import com.example.domain.model.jjugccc2024.advanced.routing.place.Place;
import com.example.domain.model.jjugccc2024.advanced.routing.place.PlaceQueue;
import com.example.domain.model.jjugccc2024.advanced.routing.routes.PathList;
import com.example.domain.model.jjugccc2024.advanced.routing.routes.RouteMap;

import java.util.List;
import java.util.Set;

/**
 * 幅優先探索
 */
class ShortestDistancesBuilder {
    // 参照情報（不変）
    final Place 出発地;
    final PathList 経路一覧;
    final RouteMap 経路マップ; // 隣接リストのマップ

    // 生成情報（可変）
    Distances 各地点までの距離 = new Distances();
    PlaceQueue 探索地点のキュー = new PlaceQueue();

    ShortestDistancesBuilder(Place 出発地, PathList 経路一覧, RouteMap 経路マップ) {
        this.出発地 = 出発地;
        this.経路一覧 = 経路一覧;
        this.経路マップ = 経路マップ;
    }

    void 幅優先で探索() {
        出発地を使って探索地点のキューと探索結果を初期化();
        while (探索地点のキュー.空でない()) {
            Place 現在地 = 探索地点のキュー.取り出し();
            未探索の地点の一覧(現在地).forEach(隣接地 -> 出発地から隣接地までの距離を追加(現在地, 隣接地));
        }
    }

    private void 出発地を使って探索地点のキューと探索結果を初期化() {
        各地点までの距離.追加(出発地, 0); // 出発地から出発地の距離はゼロ
        探索地点のキュー.追加(出発地); // 探索の開始地点
    }

    // TODO List, Setを隠蔽する？
    private List<Place> 未探索の地点の一覧(Place 現在地) {
        Set<Place> 除外する地点 = 各地点までの距離.探索済の地点();
        return 経路マップ.隣接リスト(現在地).除外(除外する地点);
    }

    private void 出発地から隣接地までの距離を追加(Place 現在地, Place 隣接地) {
        int 出発地から現在地までの距離 = 各地点までの距離.出発地からの距離(現在地);
        int 現在地から隣接地への距離 = 経路一覧.地点間の距離(現在地, 隣接地);
        各地点までの距離.追加(隣接地, 出発地から現在地までの距離 + 現在地から隣接地への距離);
        探索地点のキュー.追加(隣接地);
    }

    ShortestDistances 探索結果() {
        return new ShortestDistances(出発地, 各地点までの距離);
    }

    @Override
    public String toString() {
        return "ShortestDistanceMap{" +
                "始点=" + 出発地 +
                ", 各地点までの距離=" + 各地点までの距離 +
                '}';
    }
}
