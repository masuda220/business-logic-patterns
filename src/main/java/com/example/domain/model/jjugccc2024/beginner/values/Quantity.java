package com.example.domain.model.jjugccc2024.beginner.values;

/**
 * 数量（単位付き）
 *
 * 100グラム単位
 * 表示はキログラム（例:1.5kg）
 */
class Quantity {
    int 量;
    Unit 単位 = Unit.キログラム;

    private Quantity(int 量) {
        this.量 = 量;
    }

    static Quantity fromGram(int gram) {
        return new Quantity(gram / 100);
    }
}