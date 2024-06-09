package com.example.domain.model.jjugccc2024.advanced.proportion;

/**
 * *尺度
 */
enum ScaleType {
    実金額(1, "円", "実際の金額"),
    万分率(10_000, "bps", "ベーシスポイント：1bp = 0.01%");

    final int スケール定数;
    final String 単位表記;
    final String 説明;

    ScaleType(int スケール定数, String 単位表記, String 説明) {
        this.スケール定数 = スケール定数;
        this.単位表記 = 単位表記;
        this.説明 = 説明;
    }

    void 合計制約(SharePie 構成比) {
        if (this == 実金額) return; // 金額ベースでは合計制約はない
        if (構成比.分担量の合計() != スケール定数) throw new IllegalArgumentException("構成比の合計が不正");
    }
}
