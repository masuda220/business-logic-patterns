package com.example.domain.model.jjugccc2024.advanced.routing;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Comparator.comparingInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RouteMapTest {

    Place 東京 = new Place("東京");
    Place 神田 = new Place("神田");
    Place 御茶ノ水 = new Place("御茶ノ水");
    Place 秋葉原 = new Place("秋葉原");
    Place 池袋 = new Place("池袋");
    Place 新宿 = new Place("新宿");
    Place 品川 = new Place("品川");
    Place 渋谷 = new Place("渋谷");
    Place 三鷹 = new Place("三鷹");

    Set<Path> 経路の集合 = Set.of(
            // 中央線
            new Path(東京, 神田, 1),
            new Path(秋葉原, 御茶ノ水, 1),
            new Path(神田, 御茶ノ水, 1),
            new Path(御茶ノ水, 新宿, 1),
            new Path(新宿, 三鷹, 1),
            // 山手線 内回り
            new Path(東京, 秋葉原, 1),
            new Path(秋葉原, 池袋, 1),
            new Path(池袋, 新宿, 1),
            // 山手線 外回り
            new Path(東京, 品川, 1),
            new Path(品川, 渋谷, 1),
            new Path(渋谷, 新宿, 1)
    );

    PathList 経路一覧 = new PathList(経路の集合);
    RouteMap 隣接リスト = RouteMap.隣接リストの構築(経路一覧);
    // 現在の実装は、重み無しの探索　経由地の数で疑似的に距離を表現している
    // 重みありの探索は別の実装（例えばダイクストラ法）が必要

    @Test
    void 接続数は新宿が一番多い() {
        Map<Integer, List<Place>> 接続数 = 隣接リスト.接続数別グルーピング();

        System.out.println("接続数");
        System.out.println(接続数);

        List<Place> 接続数が最も多い = 接続数.entrySet().stream()
                .max(comparingInt(Map.Entry::getKey))
                .orElseThrow().getValue();

        assertTrue(接続数が最も多い.contains(新宿) && 接続数が最も多い.size() == 1);
    }

    @Test
    void 東京からは三鷹がもっとも遠い() {
//        final ShortestDistanceMap 各地点への最短距離 = 隣接リスト.各地点への最短距離(東京);
        final ShortestDistancesBuilder 各地点への最短距離 =
                ShortestDistancesBuilder.初期化(東京, new PathList(経路の集合), 隣接リスト);
        各地点への最短距離.幅優先で探索();

        System.out.println("各地点への最短距離");
        System.out.println(各地点への最短距離);

        ShortestDistances 各地点への最短距離の生成結果 = 各地点への最短距離.探索結果();
        Path 期待値 = new Path(東京, 三鷹, 4);

        assertEquals(期待値, 各地点への最短距離の生成結果.出発地から最も遠い地点への距離());
    }

}