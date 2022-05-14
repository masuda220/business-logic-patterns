package com.example.domain.type.enums;

/**
 * 数値の範囲
 */
class Range {
    int lowerLimit;
    int upperLimit;

    Range(int lowerLimit, int upperLimit) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    boolean includes(int target) {
        if (target < lowerLimit) return false;
        return target <= upperLimit;
    }
}
