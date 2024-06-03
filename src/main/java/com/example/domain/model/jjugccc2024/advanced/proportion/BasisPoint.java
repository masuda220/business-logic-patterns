package com.example.domain.model.jjugccc2024.advanced.proportion;

import java.util.Arrays;

/**
 * ベーシスポイント（万分率）
 */
record BasisPoint(int point) {
    private static final int スケール定数 = 100_000;
    static final BasisPoint ゼロ = new BasisPoint(0);

    BasisPoint 足す(BasisPoint 他のポイント) {
        return new BasisPoint(this.point + 他のポイント.point);
    }

    BasisPoint 合計(BasisPoint... 構成要素群) {
        int result = Arrays.stream(構成要素群).mapToInt(BasisPoint::point).sum();
        return new BasisPoint(result);
    }

    boolean スケール定数に一致() {
        return point == スケール定数;
    }

    static BasisPoint 比率(int 分子, int 分母) {
        return new BasisPoint((分子 * スケール定数) / 分母); // 端数切り捨て
    }
}
