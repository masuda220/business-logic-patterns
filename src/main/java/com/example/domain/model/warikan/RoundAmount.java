package com.example.domain.model.warikan;

import com.example.domain.type.money.Amount;

/**
 * 丸め金額(一人あたりの支払い単位)
 */
public class RoundAmount {

    public static final RoundAmount ONE_HUNDRED = new RoundAmount(100);

    int value;

    RoundAmount(int value) {
        this.value = value;
    }

    public Amount round(Amount amount) {
        return amount.divide(value).multiply(value);
    }
}
