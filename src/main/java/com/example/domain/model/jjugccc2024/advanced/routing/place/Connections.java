package com.example.domain.model.jjugccc2024.advanced.routing.place;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 地点ごとの接続数
 */
public class Connections {
    Map<Place, Integer> 地点ごとの接続数;

    public Connections(Map<Place, Integer> 地点ごとの接続数) {
        this.地点ごとの接続数 = 地点ごとの接続数;
    }

    public PlaceList 最大接続数の地点リスト() {
        int 最大接続数 = Collections.max(地点ごとの接続数.values());
        List<Place> 地点のリスト = 地点ごとの接続数.entrySet().stream()
                .filter(地点と接続数 -> 地点と接続数.getValue() == 最大接続数)
                .map(Map.Entry::getKey)
                .toList();
        return new PlaceList(地点のリスト);
    }
}
