package com.example.domain.model.jjugccc2024.advanced.routing.routes;

import com.example.domain.model.jjugccc2024.advanced.routing.place.Place;
import com.example.domain.model.jjugccc2024.advanced.routing.place.PlaceList;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toSet;

/**
 * 地点ごとの接続数
 */
public class Connections {
    Map<Place, Set<Place>> 地点ごとの隣接リスト;

    Connections(Map<Place, Set<Place>> 地点ごとの隣接リスト) {
        this.地点ごとの隣接リスト = 地点ごとの隣接リスト;
    }

    // プリミティブなメソッドを使い、説明はコメントで行なう
    public PlaceList 最大接続数を持つ地点リスト_コメントバージョン() {
        int 最大接続数 = 最大接続数();
        return 地点ごとの隣接リスト.entrySet().stream() // 地点と隣接地点リストの集合
                .filter(地点と接続数 -> 地点と接続数.getValue().size() == 最大接続数) // 隣接地点の数が最大の地点だけ絞り込む
                .map(Map.Entry::getKey) // 地点だけ取り出す
                .collect(collectingAndThen(toSet(), PlaceList::of)); // 取り出した地点を地点リストのまとめる
    }

    // 説明メソッド：実装の詳細をメソッドに隠蔽して、メソッド名で説明
    public PlaceList 最大接続数を持つ地点リスト() {
        return 地点と隣接地点リストの集合()
                .filter(隣接地点の数が最大の地点だけに絞り込む())
                .map(地点だけ取り出す())
                .collect(取り出した地点を地点リストにまとめる());
    }

    // 実装の詳細をメソッドに隠蔽
    private Stream<Map.Entry<Place, Set<Place>>> 地点と隣接地点リストの集合() {
        return 地点ごとの隣接リスト.entrySet().stream();
    }

    private Predicate<Map.Entry<Place, Set<Place>>> 隣接地点の数が最大の地点だけに絞り込む() {
        int 最大接続数 = 最大接続数();
        return 地点と接続数 -> 地点と接続数.getValue().size() == 最大接続数;
    }

    private int 最大接続数() {
        return 地点ごとの隣接リスト.values().stream()
                .mapToInt(Set::size)
                .max().orElseThrow();
    }

    private Function<Map.Entry<Place, Set<Place>>, Place> 地点だけ取り出す() {
        return Map.Entry::getKey;
    }

    private Collector<Place, Object, PlaceList> 取り出した地点を地点リストにまとめる() {
        return collectingAndThen(toSet(), PlaceList::of);
    }
}
