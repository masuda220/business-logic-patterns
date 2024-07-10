package com.example.domain.model.jjugccc2024.advanced.routing.routes;

import com.example.domain.model.jjugccc2024.advanced.routing.place.Place;
import com.example.domain.model.jjugccc2024.advanced.routing.place.PlaceList;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
        Set<Place> 地点のリスト = 地点ごとの接続数.entrySet().stream()
                .filter(地点と接続数 -> 地点と接続数.getValue() == 最大接続数)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        return PlaceList.of(地点のリスト);
    }
}
