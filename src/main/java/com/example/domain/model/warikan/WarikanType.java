package com.example.domain.model.warikan;

import com.example.domain.type.RoundingType;

/**
 * 割り勘の方法を表します。<br>
 * <table>
 *     <caption>割り勘の方法</caption>
 *     <tr><th>種類</th><th>説明</th></tr>
 *     <tr><td>{@link #不足あり}</td><td>全員の支払う金額が請求金額を超えないように割り勘をする。請求金額を下回る場合は不足金額が発生する。</td></tr>
 *     <tr><td>{@link #余りあり}</td><td>全員の支払う金額が請求金額を下回らないように割り勘ををする。請求金額を上回る場合はお釣りが発生する。</td></tr>
 * </table>
 */
public enum WarikanType {
    不足あり(RoundingType.切捨て),
    余りあり(RoundingType.切上げ);

    RoundingType roundingType;

    WarikanType(RoundingType roundingType) {
        this.roundingType = roundingType;
    }
}
