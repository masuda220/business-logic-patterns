package com.example.domain.model.warikan;

import com.example.domain.type.money.Amount;

/**
 * シンプルな割り勘
 */
public class SimpleWarikan {

    Amount totalAmount;
    Headcount headcount;

    public SimpleWarikan(Amount totalAmount, Headcount headcount) {
        this.totalAmount = totalAmount;
        this.headcount = headcount;
    }

    public Amount[] splitOfRoundUp(RoundAmount roundAmount) {
        Amount amountPerPerson = roundAmount.roundUp(amountPerPerson());
        Amount amountOfHeadcount = amountOfHeadcount(amountPerPerson);
        Amount[] result =  {amountPerPerson, amountOfHeadcount.subtract(totalAmount)};
        return result;
    }

    public Amount[] splitOfRoundDown(RoundAmount roundAmount) {
        Amount amountPerPerson = roundAmount.roundDown(amountPerPerson());
        Amount amountOfHeadcount = amountOfHeadcount(amountPerPerson);
        Amount[] result =  {amountPerPerson, totalAmount.subtract(amountOfHeadcount)};
        return result;
    }

    Amount amountPerPerson() {
        return totalAmount.divide(headcount.value);
    }

    Amount amountOfHeadcount(Amount amountPerPerson) {
        return amountPerPerson.multiply(headcount.value);
    }
}
