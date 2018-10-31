package com.example.domain.type;

import java.math.BigDecimal;

public enum RoundingType {
    四捨五入(BigDecimal.ROUND_HALF_UP), 切捨て(BigDecimal.ROUND_DOWN), 切上げ(BigDecimal.ROUND_UP);

    private int mode;

    private RoundingType(int roundingMode) {
        this.mode = roundingMode;
    }

    public int mode() {
        return mode;
    }

}
