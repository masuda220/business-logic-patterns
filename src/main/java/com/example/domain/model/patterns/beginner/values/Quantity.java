package com.example.domain.model.patterns.beginner.values;

import java.math.BigDecimal;

/**
 * 数量（単位付き）
 *
 * 100グラム単位
 * 表示はキログラム（例:1.5kg）
 */
class Quantity {
    int value;
    static final Unit unit = Unit.キログラム;

    private Quantity(int value) {
        this.value = value;
    }

    static Quantity fromGram(int gram) {
        return new Quantity(gram / 100);
    }
}