package com.example.domain.model.jjugccc2024.advanced.routing.routes;

import com.example.domain.model.jjugccc2024.advanced.routing.place.Place;
import com.example.domain.model.jjugccc2024.advanced.routing.place.PlaceList;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
                .collect(Collectors.toSet());
        return PlaceList.of(地点のリスト);
    }

     private int 最大接続数() {
        return 地点ごとの隣接リスト.values().stream()
                .mapToInt(Set::size)
                .max().orElseThrow();
    }
}
