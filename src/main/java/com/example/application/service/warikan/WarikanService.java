package com.example.application.service.warikan;

import com.example.domain.model.warikan.Headcount;
import com.example.domain.model.warikan.Warikan;
import com.example.domain.model.warikan.WarikanType;
import com.example.domain.type.money.Amount;
import com.example.domain.type.money.AmountUnit;

/**
 * 請求金額を一人あたり{@link com.example.domain.type.money.AmountUnit 金額}単位になるように割り勘をします。
 * <table>
 *     <caption>割り勘の方法</caption>
 *     <tr><th>割り勘の方法</th><th>説明</th></tr>
 *     <tr><td>不足金額のある割り勘</td><td>全員の支払う金額が請求金額を超えないように割り勘をする。請求金額を下回る場合は不足金額が発生する。</td></tr>
 *     <tr><td>余り金額のある割り勘</td><td>全員の支払う金額が請求金額を下回らないように割り勘をする。請求金額を上回る場合はお釣りが発生する。</td></tr>
 * </table>
 */
public class WarikanService {

    public Warikan warikanWithShortage(Amount totalAmount, Headcount headcount, AmountUnit amountUnit) {
        return new Warikan(totalAmount, headcount, amountUnit, WarikanType.不足あり);
    }

    public Warikan warikanWithRemainder(Amount totalAmount, Headcount headcount, AmountUnit amountUnit) {
        return new Warikan(totalAmount, headcount, amountUnit, WarikanType.余りあり);
    }
}
