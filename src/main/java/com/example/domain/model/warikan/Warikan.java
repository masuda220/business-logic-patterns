package com.example.domain.model.warikan;

import com.example.domain.type.RoundingType;
import com.example.domain.type.money.Amount;
import com.example.domain.type.money.Amount.AmountUnit;

/**
 * 割り勘
 */
public class Warikan {

    Amount totalAmount;
    Headcount headcount;
    AmountUnit amountUnit;
    RoundingType roundingType;

    Warikan(Amount totalAmount, Headcount headcount, AmountUnit amountUnit, RoundingType roundingType) {
        this.totalAmount = totalAmount;
        this.headcount = headcount;
        this.amountUnit = amountUnit;
        this.roundingType = roundingType;
    }

    public static Warikan withRemainderOf(Amount totalAmount, Headcount headcount, AmountUnit amountUnit) {
        return new Warikan(totalAmount, headcount, amountUnit, RoundingType.切上げ);
    }

    public static Warikan withShortageOf(Amount totalAmount, Headcount headcount, AmountUnit amountUnit) {
        return new Warikan(totalAmount, headcount, amountUnit, RoundingType.切捨て);
    }

    public Amount perPerson() {
        return amountPerPerson().round(amountUnit, roundingType);
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
        return totalAmount.round(headcount.value);
    }

    Amount amountOfHeadcount(Amount amountPerPerson) {
        return amountPerPerson.multiply(headcount.value);
    }
}
