package com.example.domain.model.jjugccc2024.advanced.proportion;

import java.util.List;

class SharePieByPoint {
    SharePie 分担比率;
    static final ScaledFactor 万分率 = ScaledFactor.万分率;

    private SharePieByPoint(SharePie 分担比率) {
        this.分担比率 = 分担比率;
    }

    SharePieByAmount 比例配分(Amount 対象金額) {
        // todo 詳細をSharePieに移動して、ここでは業務ルールを明示する
        List<Share> 配分結果 = 分担比率.分担構成.stream()
                .map(e -> new Share(e.出資者(), (e.値() * 対象金額.金額()) / 万分率.スケール定数))
                .toList();
        SharePie 調整前 = SharePie.構築(配分結果);

        int 合計金額 = 配分結果.stream().mapToInt(Share::値).sum();
        int 差分 = 対象金額.金額() - 合計金額;

        SharePie 調整後 = 調整前.主分担者に配分して調整(差分);

        return SharePieByAmount.of(調整後);
    }

    static SharePieByPoint 構築(SharePie 分担比率) {
        // todo この条件の詳細を隠蔽するか検討
        if (分担比率.合計() != 万分率.スケール定数) throw new IllegalArgumentException("構成比率の合計が1にならない");

        return new SharePieByPoint(分担比率);
    }
}
