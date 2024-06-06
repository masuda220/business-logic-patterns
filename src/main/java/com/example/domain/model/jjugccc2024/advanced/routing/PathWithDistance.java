package com.example.domain.model.jjugccc2024.advanced.routing;

record PathWithDistance(Path パス, int 距離) {
    PathWithDistance 逆転() {
        return new PathWithDistance(パス.逆転(), 距離);
    }

    Place 出発地() {
        return パス.出発地();
    }

    Place 到着地() {
        return パス.到着地();
    }

}
