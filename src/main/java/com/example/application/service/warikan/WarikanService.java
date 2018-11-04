package com.example.application.service.warikan;

import com.example.domain.model.warikan.Headcount;
import com.example.domain.model.warikan.Warikan;
import com.example.domain.type.money.Amount;
import com.example.domain.type.money.Amount.AmountUnit;

/**
 * 割り勘サービス
 */
public class WarikanService {

    public Warikan warikanWithShortage(Amount totalAmount, Headcount headcount, AmountUnit amountUnit) {
        return Warikan.withShortageOf(totalAmount, headcount, amountUnit);
    }

    public Warikan warikanWithRemainder(Amount totalAmount, Headcount headcount, AmountUnit amountUnit) {
        return Warikan.withRemainderOf(totalAmount, headcount, amountUnit);
    }
}
