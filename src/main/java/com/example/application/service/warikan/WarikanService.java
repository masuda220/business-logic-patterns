package com.example.application.service.warikan;

import com.example.domain.model.warikan.Headcount;
import com.example.domain.model.warikan.Warikan;
import com.example.domain.model.warikan.WarikanType;
import com.example.domain.type.money.Amount;
import com.example.domain.type.money.AmountUnit;

/**
 * 割り勘サービス
 * TODO 割り勘の説明をJavadocで記載する
 * 一人の金額の端数を切り捨てる : 支払に足りない金額が発生する
 * 一人の金額の端数を切り上げる : 支払いべき金額を上回る
 */
public class WarikanService {

    public Warikan warikanWithShortage(Amount totalAmount, Headcount headcount, AmountUnit amountUnit) {
        return new Warikan(totalAmount, headcount, amountUnit, WarikanType.切捨て);
    }

    public Warikan warikanWithRemainder(Amount totalAmount, Headcount headcount, AmountUnit amountUnit) {
        return new Warikan(totalAmount, headcount, amountUnit, WarikanType.切上げ);
    }
}
