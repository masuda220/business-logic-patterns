package com.example.application.service.warikan;

import com.example.domain.model.warikan.Headcount;
import com.example.domain.model.warikan.RoundAmount;
import com.example.domain.model.warikan.SimpleWarikan;
import com.example.domain.type.money.Amount;

/**
 * 割り勘サービス
 */
public class Warikan {

    public Amount[] warikan(Amount totalAmount, Headcount headcount) {
        SimpleWarikan simpleWarikan = new SimpleWarikan(totalAmount, headcount);
        return simpleWarikan.splitOfRoundUp(RoundAmount.ONE_HUNDRED);
    }

    public Amount[] warikanRoundDown(Amount totalAmount, Headcount headcount) {
        SimpleWarikan simpleWarikan = new SimpleWarikan(totalAmount, headcount);
        return simpleWarikan.splitOfRoundDown(RoundAmount.ONE_HUNDRED);
    }
}
