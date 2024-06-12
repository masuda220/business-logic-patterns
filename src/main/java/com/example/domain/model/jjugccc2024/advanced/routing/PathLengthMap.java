package com.example.domain.model.jjugccc2024.advanced.routing;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 出発地から各地点までの距離
 */
class PathLengthMap {
    Place 最初の出発地;
    Map<Place, Integer> 各地点までの距離のマップ;
    static final int 未探索 = -1;
    Map<Path, Integer> パスの長さのマップ;

    private PathLengthMap(Place 最初の出発地,
                          Map<Place, Integer> 各地点までの距離のマップ,
                          Map<Path, Integer> パスの長さのマップ) {
        this.最初の出発地 = 最初の出発地;
        this.各地点までの距離のマップ = 各地点までの距離のマップ;
        this.各地点までの距離のマップ.put(最初の出発地, 0); // 出発地から出発地の距離はゼロ
        this.パスの長さのマップ = パスの長さのマップ;
    }

    void 更新(Place 出発地, Place 到達地) {
        int 出発地までの距離 = 各地点までの距離のマップ.get(出発地) ;
        int 出発地から到達地への長さ = パスの長さのマップ.get(new Path(出発地, 到達地));
        各地点までの距離のマップ.put(到達地, 出発地までの距離 + 出発地から到達地への長さ);
    }

    boolean 未探索(Place 地点) {
        return 各地点までの距離のマップ.get(地点) == 未探索;
    }

    PathWithDistance 最初の出発地点からの最も遠い地点への距離() {
        Map.Entry<Place,Integer> 最大 =
                各地点までの距離のマップ.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue)).orElseThrow();
        return new PathWithDistance(new Path(最初の出発地, 最大.getKey()), 最大.getValue());
    }

    static PathLengthMap 初期化(Place 出発地,
                                Collection<Place> 地点リスト,
                                Map<Path, Integer> パスの長さのマップ) {
        Map<Place, Integer> 各地点までの距離のマップ = 地点リスト.stream()
                .collect(Collectors.toMap(地点 -> 地点, 地点 -> 未探索));

        return new PathLengthMap(出発地, 各地点までの距離のマップ, パスの長さのマップ);
    }

    @Override
    public String toString() {
        return "PathLengthMap{" +
                "出発地=" + 最初の出発地 +
                ", pathLengthMap=" + 各地点までの距離のマップ +
                '}';
    }
}
