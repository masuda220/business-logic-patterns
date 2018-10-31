package com.example.domain.type.hour.unit;

import com.example.domain.type.hour.Minute;

public enum MinuteUnit {

    // FIXME アンダーバー始まりでない良い名前をつけたい
    _1分(1), _5分(5), _10分(10), _15分(15), _20分(20), _30分(30), _60分(60);

    private int value;

    private MinuteUnit(int value) {
        this.value = value;
    }

    public Minute minute() {
        return new Minute(value);
    }

}
