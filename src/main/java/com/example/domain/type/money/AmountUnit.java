package com.example.domain.type.money;

import com.example.domain.type.RoundingType;

import java.math.BigDecimal;

public enum AmountUnit {
    ONE_HUNDRED(100),
    ONE_THOUSAND(1000);

    int value;

    AmountUnit(int value) {
        this.value = value;
    }

    public Amount round(Amount amount, RoundingType roundingType) {
        BigDecimal quotient = new BigDecimal(amount.value).divide(new BigDecimal(value), roundingType.mode());
        return new Amount(quotient.longValue()).multiply(value);
    }
}
