package com.example.quantity.unit;

/**
 * 単位
 */
public interface Unit {

    int piece();

    default boolean isPiece() {
        return piece() == 1;
    }

    default boolean isEqualTo(Unit other) {
        return this.getClass() == other.getClass();
    }

}
