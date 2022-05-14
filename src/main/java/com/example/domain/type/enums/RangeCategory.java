package com.example.domain.type.enums;

/**
 * 範囲(from-to)の集合
 *
 * ある値が含まれる範囲を特定する
 * それぞれの範囲は、「下限値」と「次の範囲の下限値 - 1」
 * 範囲は、昇順に定義する
 *
 */
public enum RangeCategory {
    one(0),
    two(10),
    three(50);
    int maxUpperLimit = 100; // 全体の上限

    int lowerLimit;
    RangeCategory(int lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public RangeCategory categoryFor(int target) {
        for (RangeCategory each : values()) {
            if (includes(target)) return each;
        }
        throw new IllegalArgumentException("out of range:" + target);
    }

    boolean includes(int target) {
        Range range = new Range(lowerLimit, upperLimit());
        return range.includes(target);
    }

    int upperLimit() {
        if (this.isLast()) return maxUpperLimit;
        return next().lowerLimit - 1;
    }

    RangeCategory[] source = values();
    RangeCategory next() {
        return source[this.ordinal() + 1];
    }

    boolean isLast() {
        return this.ordinal() == source.length - 1;
    }
}
