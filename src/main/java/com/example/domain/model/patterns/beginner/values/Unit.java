package com.example.domain.model.patterns.beginner.values;

/**
 * 計量単位
 */
enum Unit {
    キログラム("kg"),
    グラム("g");

    final String 単位記号;

    Unit(String 単位記号) {
        this.単位記号 = 単位記号;
    }
}
