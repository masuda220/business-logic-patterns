package com.example.domain.model.jjugccc2024.advanced.proportion;

public enum ScaledFactor {
    実金額(1, "円"),
    万分率(100_000, "bps");

    final int スケール定数;
    final String 単位表記;

    ScaledFactor(int スケール定数, String 単位表記) {
        this.スケール定数 = スケール定数;
        this.単位表記 = 単位表記;
    }
}
