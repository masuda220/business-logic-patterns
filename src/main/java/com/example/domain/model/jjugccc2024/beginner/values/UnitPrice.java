package com.example.domain.model.jjugccc2024.beginner.values;

/**
 * 単価
 */
class UnitPrice {
    Amount 金額;
    static final Unit 単位 = Unit.キログラム;

    Amount 掛ける(Quantity 数量) {
        if (単位 != 数量.単位) throw new IllegalArgumentException("単位の不一致");
        return new Amount(金額.額() * 数量.量);
    }
}
