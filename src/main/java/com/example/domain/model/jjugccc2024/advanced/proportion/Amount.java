package com.example.domain.model.jjugccc2024.advanced.proportion;

import java.util.Collection;

/**
 *
 */
record Amount(int 金額) {
    Amount sum(Collection<Amount> 金額の集まり) {
        return new Amount(金額の集まり.stream().mapToInt(Amount::金額).sum());
    }

    BasisPoint 比率(Amount 分母) {
        return BasisPoint.比率(this.金額, 分母.金額);
    }

    public Amount 足す(Amount 他の金額) {
        return new Amount(this.金額 + 他の金額.金額);
    }
}
