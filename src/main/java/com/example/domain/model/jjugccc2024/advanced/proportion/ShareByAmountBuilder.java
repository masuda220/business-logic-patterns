package com.example.domain.model.jjugccc2024.advanced.proportion;

import java.util.HashMap;
import java.util.Map;

/**
 * 金額構成オブジェクトのビルダー（ミュータブル）
 */
class ShareByAmountBuilder {
    private Map<ShareHolder, Amount> 金額構成 = new HashMap<ShareHolder, Amount>();

    void 追加(ShareHolder shareHolder, Amount 金額) {
        if (金額構成.containsKey(shareHolder)) 現在の金額に追加(shareHolder, 金額);
        金額構成.put(shareHolder, 金額);
    }

    private void 現在の金額に追加(ShareHolder shareHolder, Amount 金額) {
        金額構成.put(shareHolder, 金額構成.get(shareHolder).足す(金額));
    }

    ShareByAmount toShareByAmount() {
        return ShareByAmount.of(金額構成);
    }
}
