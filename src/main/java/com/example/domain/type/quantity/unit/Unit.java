package com.example.domain.type.quantity.unit;

/**
 * 単位
 */
public class Unit {
    int conversionFactor;
    String name;
    String symbol;

    private Unit(int conversionFactor, String name, String symbol) {
        this.conversionFactor = conversionFactor;
        this.name = name;
        this.symbol = symbol;
    }

    public int convert(int number, Unit target) {
        UnitConverter unitConverter = new UnitConverter(this, target);
        return unitConverter.convert(number);
    }

    public String show(long value) {
        return String.format("%d%s", value, name);
    }

    public static Unit PIECE = new Unit(1, "個", "pcs");

    public static Unit box(int factor) {
        return new Unit(factor, "箱", "box");
    }

    public static Unit carton(int factor, Unit unit) {
        int conversionFactor = unit.conversionFactor * factor;
        return new Unit(conversionFactor, "カートン", "ctn");
    }
}
