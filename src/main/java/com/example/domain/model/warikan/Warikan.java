package com.example.domain.model.warikan;

import com.example.domain.type.money.Amount;
import com.example.domain.type.money.AmountUnit;

/**
 * 割り勘
 */
public class Warikan {

    Amount totalAmount;
    Headcount headcount;
    AmountUnit amountUnit;
    WarikanType warikanType;

    public Warikan(Amount totalAmount, Headcount headcount, AmountUnit amountUnit, WarikanType warikanType) {
        this.totalAmount = totalAmount;
        this.headcount = headcount;
        this.amountUnit = amountUnit;
        this.warikanType = warikanType;
    }

    public Amount perPerson() {
        return amountUnit.round(amountPerPerson(), warikanType.roundingType);
    }

    public Amount remainder() {
        Amount amountOfHeadcount = amountOfHeadcount(perPerson());
        if (totalAmount.isGreaterOrEqualTo(amountOfHeadcount)) return new Amount(0);
        return amountOfHeadcount.subtract(totalAmount);
    }

    public Amount shortage() {
        Amount amountOfHeadcount = amountOfHeadcount(perPerson());
        if (totalAmount.isLessOrEqualTo(amountOfHeadcount)) return new Amount(0);
        return totalAmount.subtract(amountOfHeadcount);
    }

    Amount amountPerPerson() {
        return totalAmount.divide(headcount.value);
    }

    Amount amountOfHeadcount(Amount amountPerPerson) {
        return amountPerPerson.multiply(headcount.value);
    }
}
