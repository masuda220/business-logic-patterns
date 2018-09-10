package com.example.domain.type.quantity.unit;

/**
 * 箱単位
 */
public class BoxUnit implements Unit {

    int piece;

    public BoxUnit(int piece) {
        this.piece = piece;
    }

    @Override
    public int piece() {
        return piece;
    }

}
