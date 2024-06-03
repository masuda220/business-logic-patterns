package com.example.domain.model.jjugccc2024.advanced.proportion;

import java.util.Map;

class ShareByBasisPoint {
    Map<ShareHolder, BasisPoint> 構成比;

    private ShareByBasisPoint(Map<ShareHolder, BasisPoint> 構成比) {
        this.構成比 = 構成比;
    }

    static ShareByBasisPoint 調整して生成(Map<ShareHolder, BasisPoint> 調整前) {
        BasisPoint 合計 = 調整前.values().stream().reduce(BasisPoint.ゼロ, BasisPoint::足す);
        if (合計.スケール定数に一致()) return new ShareByBasisPoint(調整前);

        return new ShareByBasisPoint(null); // 暫定
    }
}
