package com.example.application.service.warikan;

import com.example.domain.model.warikan.Bill;
import com.example.domain.model.warikan.Headcount;
import com.example.domain.type.money.Amount;

/**
 * 割り勘サービス
 */
public class Warikan {

    public Amount[] warikan(Bill totalBill, Headcount headcount) {
        return totalBill.split(headcount);
    }
}
