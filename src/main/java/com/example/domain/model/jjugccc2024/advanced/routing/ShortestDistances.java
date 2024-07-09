package com.example.domain.model.jjugccc2024.advanced.routing;

import com.example.domain.model.jjugccc2024.advanced.routing.place.Distances;
import com.example.domain.model.jjugccc2024.advanced.routing.place.Place;
import com.example.domain.model.jjugccc2024.advanced.routing.place.PlaceList;

/**
 * 各地点までの最短距離
 */
class ShortestDistances {

    Place 出発地;
    Distances 各地点までの距離;

    ShortestDistances(Place 出発地, Distances 各地点までの距離) {
        this.出発地 = 出発地;
        this.各地点までの距離 = 各地点までの距離;
    }

    int 最も遠い地点までの距離() {
        return 各地点までの距離.最も遠い地点への距離();
    }

    PlaceList 最も遠い地点() {
        return 各地点までの距離.最も遠い地点のリスト();
    }

    @Override
    public String toString() {
        return "ShortestDistances{" +
                "出発地=" + 出発地 +
                ", 各地点までの距離=" + 各地点までの距離 +
                '}';
    }
}
