package com.example.domain.type.ratio;

/**
 * 百分率
 */
public class Percentage {
    int value;
    static final int 百 = 100;
    static final int 半分 = 50; // 四捨五入(half up)用

    public Percentage(int value) {
        this.value = value;
    }

    // TODO Money　クラスでの使い方のサンプル追加 : Money#multiply(Percentage percentage)
    public int multiply(int other) {
        return (value * other) / 百 ; // 切り捨て
    }

    public int multiplyHalfUp(int other) {
        return ((value * other) + 半分) / 百;
    }

    public int multiplyUp(int other) {
        return ((value * other) + (百 - 1)) / 百;
    }

    @Override
    public String toString() {
        return String.format("%d%%", value);
    }
}
