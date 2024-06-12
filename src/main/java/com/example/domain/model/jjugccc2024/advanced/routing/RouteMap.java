package com.example.domain.model.jjugccc2024.advanced.routing;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * 経路
 */
class RouteMap {
    Map<Place, List<Place>> 隣接リストのマップ;
    Map<Path, Integer> パスの長さ;

    RouteMap(Map<Place, List<Place>> 隣接リストのマップ, Map<Path, Integer> パスの長さ) {
        this.隣接リストのマップ = 隣接リストのマップ;
        this.パスの長さ = パスの長さ;
    }

    PathLengthMap 各地点への距離(Place 出発地) {
        PathLengthMap 出発地からの距離のマップ = PathLengthMap.初期化(出発地, 隣接リストのマップ.keySet(), パスの長さ);
        幅優先で探索して各地点への距離を更新(出発地, 出発地からの距離のマップ);

        return 出発地からの距離のマップ;
    }

    Map<Integer, List<Place>> 分岐数別グルーピング() {
        return 隣接リストのマップ.entrySet().stream()
                .collect(groupingBy(entry -> entry.getValue().size(), mapping(Map.Entry::getKey, toList())));
    }

    private void 幅優先で探索して各地点への距離を更新(Place 最初の出発地, PathLengthMap 出発地からの距離のマップ) {
        Queue<Place> 探索地点のキュー = new LinkedList<>();
        探索地点のキュー.add(最初の出発地); // 探索の開始地点

        while (!探索地点のキュー.isEmpty()) {
            Place 探索地点 = 探索地点のキュー.remove(); // 先頭を取り出す
            Stream<Place> 探索地に隣接する未探索の地点 = 未探索の隣接地点のリスト(探索地点, 出発地からの距離のマップ);
            探索地に隣接する未探索の地点.forEach(隣接地点 -> {
                        出発地からの距離のマップ.更新(探索地点, 隣接地点);
                        探索地点のキュー.add(隣接地点);
                    }
            );
        }
    }

    private Stream<Place> 未探索の隣接地点のリスト(Place 出発地, PathLengthMap 各地点への距離のマップ) {
        return 隣接リストのマップ.get(出発地).stream()
                .filter(各地点への距離のマップ::未探索);
    }

    @Override
    public String toString() {
        return "RouteMap{" +
                "routeMap=" + 隣接リストのマップ +
                '}';
    }
}
