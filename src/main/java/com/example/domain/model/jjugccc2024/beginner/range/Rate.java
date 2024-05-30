package com.example.domain.model.jjugccc2024.beginner.range;

class DiscountRate {
    int 割引率; // 百分率
    static final int 割引率上限 = 30;

    private DiscountRate(int 割引率) {
        this.割引率 = 割引率;
    }

    int 適用する(int 元金額) {
        return 元金額 * (100 - 割引率) / 100; // 端数は切り捨て
    }

    static DiscountRate of(int 割引率) {
        if (割引率 < 0) throw new IllegalArgumentException("不適切な割引率");
        if (割引率 >= 割引率上限) throw new IllegalArgumentException("割引率上限オーバー");
        return new DiscountRate(割引率);
    }
}