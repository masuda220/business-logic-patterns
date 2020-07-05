package com.example.domain.type;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 丸めの種類
 */
public enum RoundingType {
    四捨五入(RoundingMode.HALF_UP),
    切捨て(RoundingMode.DOWN),
    切上げ(RoundingMode.UP);

    private RoundingMode mode;

    private RoundingType(RoundingMode roundingMode) {
        this.mode = roundingMode;
    }

    public RoundingMode mode() {
        return mode;
    }

}
