package com.example.domain.model.jjugccc2024.advanced.routing;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * 経路
 */
public class RouteMap {
    Map<Place, List<Place>> 隣接リストのマップ;
    Map<Path, Integer> パス間の距離;

    RouteMap(Map<Place, List<Place>> 隣接リストのマップ, Map<Path, Integer> パス間の距離) {
        this.隣接リストのマップ = 隣接リストのマップ;
        this.パス間の距離 = パス間の距離;
    }

    PathLengthMap 最短経路マップ(Place 出発地) {
        PathLengthMap 各地点への最短距離のマップ = PathLengthMap.初期化(出発地, 隣接リストのマップ.keySet());
        幅優先で探索して各地点への最短距離を更新(出発地, 各地点への最短距離のマップ);

        return 各地点への最短距離のマップ;
    }

    Map<Integer, List<Place>> 分岐数別グルーピング() {
        return 隣接リストのマップ.entrySet().stream()
                .collect(groupingBy(entry -> entry.getValue().size(), mapping(Map.Entry::getKey, toList())));
    }

    private void 幅優先で探索して各地点への最短距離を更新(Place 最初の出発地,
                                                          PathLengthMap 各地点への最短距離のマップ) {
        Queue<Place> 探索すべき地点のキュー = new LinkedList<>();
        探索すべき地点のキュー.add(最初の出発地); // 探索の開始地点

        while (!探索すべき地点のキュー.isEmpty()) {
            Place 探索中の出発地 = 探索すべき地点のキュー.remove(); // 先頭を取り出す
            未探索の隣接地点のリスト(探索中の出発地, 各地点への最短距離のマップ)
                    .forEach(探索中の到達地 -> {
                        各地点への最短距離のマップ.更新(探索中の到達地, 探索中の出発地, パス間の距離);
                        探索すべき地点のキュー.add(探索中の到達地);
                      }
                    );
        }
    }

    private Stream<Place> 未探索の隣接地点のリスト(Place 探索中の出発地, PathLengthMap 各地点への最短距離のマップ) {
        return 隣接リストのマップ.get(探索中の出発地).stream()
                .filter(各地点への最短距離のマップ::未探索);
    }

    @Override
    public String toString() {
        return "RouteMap{" +
                "routeMap=" + 隣接リストのマップ +
                '}';
    }
}
