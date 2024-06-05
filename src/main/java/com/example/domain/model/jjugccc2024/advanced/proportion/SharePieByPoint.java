package com.example.domain.model.jjugccc2024.advanced.proportion;

/**
 * 万分率での構成比
 */
class SharePieByPoint {
    SharePie 構成比;
    static final ScaleType 尺度 = ScaleType.万分率;

    private SharePieByPoint(SharePie 構成比) {
        this.構成比 = 構成比;
    }

    SharePieByAmount 比例配分(Amount 対象金額) {
        SharePie 単純配分_金額ベース = 構成比.掛ける(対象金額.金額()).割る(尺度.スケール定数);
        SharePie 端数調整済_金額ベース = 単純配分_金額ベース.端数を最大出資者に割り当てて調整(対象金額.金額());
        return SharePieByAmount.of(端数調整済_金額ベース);
    }

    static SharePieByPoint of(SharePie 構成比) {
        尺度.合計制約(構成比); // 制約の強制
        return new SharePieByPoint(構成比);
    }
}
