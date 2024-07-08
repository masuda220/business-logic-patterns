package com.example.domain.model.jjugccc2024.advanced.routing;

import com.example.domain.model.jjugccc2024.advanced.routing.place.Connections;
import com.example.domain.model.jjugccc2024.advanced.routing.place.Place;
import com.example.domain.model.jjugccc2024.advanced.routing.place.PlaceList;
import com.example.domain.model.jjugccc2024.advanced.routing.routes.Path;
import com.example.domain.model.jjugccc2024.advanced.routing.routes.PathList;
import com.example.domain.model.jjugccc2024.advanced.routing.routes.RouteMap;
import org.junit.jupiter.api.Test;

import java.util.Set;

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
    // この実装は、重み無しの探索　経由地の数で疑似的に距離を表現している
    // 重みありの探索は別の実装（例えばダイクストラ法）が必要

    @Test
    void 接続数は新宿が一番多い() {
        Connections 地点別の接続数 = 隣接リスト.接続数();
        PlaceList 最大接続数の地点リスト = 地点別の接続数.最大接続数の地点リスト();

        assertTrue(最大接続数の地点リスト.地点を含む(新宿) && 最大接続数の地点リスト.地点数() == 1);
    }

    @Test
    void 東京からは三鷹がもっとも遠い() {
        final ShortestDistancesBuilder 各地点への最短距離 =
                ShortestDistancesBuilder.初期化(東京, new PathList(経路の集合), 隣接リスト);
        各地点への最短距離.幅優先で探索();

        ShortestDistances 各地点への最短距離の生成結果 = 各地点への最短距離.探索結果();
        System.out.println(各地点への最短距離の生成結果);

        int 最も遠い地点までの距離 = 各地点への最短距離の生成結果.最も遠い地点までの距離();
        PlaceList 最も遠い地点 = 各地点への最短距離の生成結果.最も遠い地点();
        assertEquals(4, 最も遠い地点までの距離);
        assertTrue(最も遠い地点.地点を含む(三鷹));
    }

}