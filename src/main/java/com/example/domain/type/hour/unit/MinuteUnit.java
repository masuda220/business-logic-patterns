package com.example.domain.type.hour.unit;

import com.example.domain.type.hour.Minute;

public enum MinuteUnit {

    ONE(1), FIVE(5), TEN(10), FIFTEEN(15), TWENTY(20), THIRTY(30), SIXTY(60);

    private int value;

    private MinuteUnit(int value) {
        this.value = value;
    }

    public Minute minute() {
        return new Minute(value);
    }

}
