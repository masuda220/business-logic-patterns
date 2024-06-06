package com.example.domain.model.jjugccc2024.advanced.routing;

/**
 * ある場所から次の場所
 */
record Path(Place 出発地, Place 到着地) {
    Path 逆転() {
        return new Path(到着地, 出発地);
    }
}
