package com.example.domain.model.jjugccc2024.advanced.routing.routes;

import com.example.domain.model.jjugccc2024.advanced.routing.place.Place;
import com.example.domain.model.jjugccc2024.advanced.routing.place.PlaceList;

import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * 地点ごとの接続数
 */
public class Connections {
    Map<Place, Set<Place>> 地点ごとの隣接リスト;

    Connections(Map<Place, Set<Place>> 地点ごとの隣接リスト) {
        this.地点ごとの隣接リスト = 地点ごとの隣接リスト;
    }

    public PlaceList 最大接続数の地点リスト() {
        int 最大接続数 = 最大接続数();
        Set<Place> 地点のリスト = 地点ごとの隣接リスト.entrySet().stream()
                .filter(地点と隣接リスト -> 地点と隣接リスト.getValue().size() == 最大接続数)
                .map(Map.Entry::getKey)
                .collect(toSet());
        return PlaceList.of(地点のリスト);
    }

    // TODO PlaceListをreduceする実験結果の評価
    private int 最大接続数() {
        return 地点ごとの隣接リスト.values().stream()
                .map(PlaceList::of) // 前処理
                .reduce(PlaceList.of(Set.of()), PlaceList::地点数が多い方)// 選択
                .地点数(); // 結果
    }
}
