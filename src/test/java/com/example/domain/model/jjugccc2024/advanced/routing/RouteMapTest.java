package com.example.domain.model.jjugccc2024.advanced.routing;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RouteMapTest {

    Place 東京 = new Place("東京");
    Place 神田 = new Place("神田");
    Place 御茶ノ水 = new Place("御茶ノ水");
    Place 秋葉原 = new Place("秋葉原");
    Place 池袋 = new Place("池袋");
    Place 新宿 = new Place("新宿");

    Set<PathWithDistance> 経路の集合 = Set.of(
            new PathWithDistance(new Path(東京, 神田), 1),
            new PathWithDistance(new Path(東京, 秋葉原), 1),
            new PathWithDistance(new Path(秋葉原, 池袋), 1),
            new PathWithDistance(new Path(秋葉原, 御茶ノ水), 1),
            new PathWithDistance(new Path(神田, 御茶ノ水), 1),
            new PathWithDistance(new Path(御茶ノ水, 新宿), 1),
            new PathWithDistance(new Path(池袋, 新宿), 1)
    );

    @Test
    void create() {
        RouteMap routeMap = RouteMapFactory.隣接リストの構築(経路の集合);

        Place 出発地点 = new Place("東京");
        PathLengthMap 各地点までの最短距離のマップ = routeMap.最短経路マップ(東京);

        // 現在の実装は、重み無しの探索
        // 距離に1以外を設定した探索は、例えばダイクストラ法の実装が必要

        System.out.println(各地点までの最短距離のマップ);

        PathWithDistance 期待値 = new PathWithDistance(new Path(東京, 新宿), 3);
        assertEquals(期待値, 各地点までの最短距離のマップ.出発地点からの最も遠い地点への距離());
    }
}