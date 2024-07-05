package com.example.domain.model.jjugccc2024.advanced.routing;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toList;

/**
 * 経路の集合
 */
class PathList {
    Set<Path> 経路の集合;

    PathList(Set<Path> 経路の集合) {
        this.経路の集合 = 経路の集合;
    }

    int 地点間の距離(Place 現在地, Place 隣接地) {
        Path result = 経路の集合.stream()
                .filter(経路 -> 経路.一致(現在地, 隣接地 )).findFirst().orElseThrow();
        return result.距離();
    }

    PathList 逆方向の経路一覧() {
        Set<Path> 逆方向の経路の集合 = 経路の集合.stream()
                .map(Path::始点と終点の入れ替え).collect(Collectors.toSet());
        return new PathList(逆方向の経路の集合);
    }

    PathList 合成(PathList 合成する経路一覧) {
        Set<Path> 全ての経路 = Stream
                .concat(経路の集合.stream(), 合成する経路一覧.経路の集合.stream()).collect(toSet());
        return new PathList(全ての経路);
    }

    Map<Place, List<Place>> 隣接リスト() {
        return 経路の集合.stream()
            .collect(groupingBy(Path::始点, mapping(Path::終点, toList())));
    }

}
