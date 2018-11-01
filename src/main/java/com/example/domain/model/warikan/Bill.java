package com.example.domain.model.warikan;

import com.example.domain.type.money.Amount;

/**
 * 請求総額
 */
public class Bill {
    Amount value;

    Bill(Amount value) {
        this.value = value;
    }

    public static Bill of(Amount amount) {
        return new Bill(amount);
    }

    public Amount[] split(Headcount headcount) {
        Amount amountPerPerson = split(headcount, RoundAmount.ONE_HUNDRED);
        Amount[] result =  {amountPerPerson, remainder(amountPerPerson, headcount)};
        return result;
    }

    Amount split(Headcount headcount, RoundAmount roundAmount) {
        Amount amountPerPerson = value.divide(headcount.value);
        return roundAmount.round(amountPerPerson);
    }

    Amount remainder(Amount amountPerPerson, Headcount headcount) {
        Amount amountOfHeadcount= amountPerPerson.multiply(headcount.value);
        return value.subtract(amountOfHeadcount);
    }
}
