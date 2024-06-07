package com.example.domain.model.jjugccc2024.advanced.routing;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 出発地と各地点までの距離
 */
class PathLengthMap {
    Place 最初の出発地;
    Map<Place, Integer> 各地点までの距離のマップ;
    static final int 未探索 = -1;

    private PathLengthMap(Place 最初の出発地, Map<Place, Integer> 各地点までの距離のマップ) {
        this.最初の出発地 = 最初の出発地;
        this.各地点までの距離のマップ = 各地点までの距離のマップ;
        this.各地点までの距離のマップ.put(最初の出発地, 0); // 出発地から出発地の距離はゼロ
    }

    void 更新(Place 探索中の到達地, Place 探索中の出発地, Map<Path, Integer> パス間の距離) {
        int 探索中の出発地までの距離 = 各地点までの距離のマップ.get(探索中の出発地) ;
        int 追加する距離 = パス間の距離.get(new Path(探索中の出発地, 探索中の到達地));
        各地点までの距離のマップ.put(探索中の到達地, 探索中の出発地までの距離 + 追加する距離);
    }

    boolean 未探索(Place 地点) {
        return 各地点までの距離のマップ.get(地点) == 未探索;
    }

    PathWithDistance 出発地点からの最も遠い地点への距離() {
        Map.Entry<Place,Integer> 最大 =
                各地点までの距離のマップ.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue)).orElseThrow();
        return new PathWithDistance(new Path(最初の出発地, 最大.getKey()), 最大.getValue());
    }

    static PathLengthMap 初期化(Place 出発地, Collection<Place> 地点リスト) {
        Map<Place, Integer> 最短経路マップ = 地点リスト.stream()
                .collect(Collectors.toMap(地点 -> 地点, 地点 -> 未探索));

        return new PathLengthMap(出発地, 最短経路マップ);
    }

    @Override
    public String toString() {
        return "PathLengthMap{" +
                "出発地=" + 最初の出発地 +
                ", pathLengthMap=" + 各地点までの距離のマップ +
                '}';
    }
}
