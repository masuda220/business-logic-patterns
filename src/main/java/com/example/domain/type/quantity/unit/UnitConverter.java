package com.example.domain.type.quantity.unit;

class UnitConverter {
    Unit source;
    Unit target;

    UnitConverter(Unit source, Unit target) {
        this.source = source;
        this.target = target;
    }

    int convert(int value) {
        int targetFactor = target.conversionFactor;
        int sourceFactor = source.conversionFactor;

        int base = value * sourceFactor;
        int remainder = base % targetFactor;
        if (remainder != 0) throw new IllegalArgumentException("変換できない");

        return base / targetFactor;
    }
}
