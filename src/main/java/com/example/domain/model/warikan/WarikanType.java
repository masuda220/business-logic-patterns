package com.example.domain.model.warikan;

import com.example.domain.type.RoundingType;

/**
 * 一人あたりの支払額の丸めを表わす
 */
public enum WarikanType {
    四捨五入(RoundingType.四捨五入),
    切捨て(RoundingType.切捨て),
    切上げ(RoundingType.切上げ);

    RoundingType roundingType;

    WarikanType(RoundingType roundingType) {
        this.roundingType = roundingType;
    }
}
